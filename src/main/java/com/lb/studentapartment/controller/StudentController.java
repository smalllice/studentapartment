package com.lb.studentapartment.controller;

import com.alibaba.fastjson.JSON;
import com.lb.studentapartment.bean.Student;
import com.lb.studentapartment.bean.Studentchuxing;
import com.lb.studentapartment.bean.StudentchuxingFI;
import com.lb.studentapartment.bean.Studentgetchuxing;
import com.lb.studentapartment.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 标题：类名称:StudentController
 * 说明：描述一下类的作用TODO
 * 时间：2022/5/12 13:41
 * 作者 @author BGG
 */
@RestController
public class StudentController {

    @Autowired
    private UserMapper userMapper;

    //查询自己出行记录
    @RequestMapping("getMychuxing")
    public String getmychuxing(Studentgetchuxing chuxing){
        List<StudentchuxingFI> personchuxing = userMapper.getPersonchuxing(chuxing.getSid(), chuxing.getFtime(), chuxing.getLtime());
        ArrayList<Studentchuxing> studentchuxings = new ArrayList<>();
        for (int i=0;i<personchuxing.size();i++){
            Student person = userMapper.getPerson(personchuxing.get(i).getSid());

            Studentchuxing studentchuxing = new Studentchuxing();
            studentchuxing.setSid(personchuxing.get(i).getSid());
            studentchuxing.setSname(person.getSname());
            studentchuxing.setSphone(person.getSphone());
            studentchuxing.setSnumber(person.getSnumber());
            studentchuxing.setHid(person.getHid());
            studentchuxing.setHname(person.getHname());
            studentchuxing.setHtime(personchuxing.get(i).getHtime());
            studentchuxing.setHstate(personchuxing.get(i).getHstate());

            studentchuxings.add(i,studentchuxing);
        }

        HashMap<String, Object> res = new HashMap<>();
        res.put("data",studentchuxings);
        String s = JSON.toJSONString(res);
        return s;
    }
}
