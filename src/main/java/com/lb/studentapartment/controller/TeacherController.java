package com.lb.studentapartment.controller;

import com.alibaba.fastjson.JSON;
import com.lb.studentapartment.bean.*;
import com.lb.studentapartment.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 标题：类名称:TeacherController
 * 说明：描述一下类的作用TODO
 * 时间：2022/5/10 10:35
 * 作者 @author BGG
 */
@RestController
public class TeacherController {

    @Autowired
    private UserMapper userMapper;

    //查询班上学生信息
    @RequestMapping("/queryMystudent")
    public String getTstudent(Mystudent mystudent){
        //System.out.println(mystudent);
        int mystudentCounts = userMapper.getMystudentCounts("%"+mystudent.getSid()+"%", mystudent.getTid());
        int pageStart=(mystudent.getPageNum()-1)*mystudent.getPageSize();
        List<Student> list = userMapper.getMystudent("%"+mystudent.getSid()+"%", mystudent.getTid(), pageStart, mystudent.getPageSize());

//        for (Student student : list) {
//            System.out.println(student);
//        }
//        System.out.println("pageStart"+pageStart);
//        System.out.println("mystudentCounts"+mystudentCounts);

        HashMap<String, Object> res = new HashMap<>();
        res.put("mystudentCounts",mystudentCounts);
        res.put("data",list);
        String s = JSON.toJSONString(res);
        return s;
    }

    //查询班上学生出行的情况
    @RequestMapping("/queryMylatestudent")
    public String getMylate(Mylatestudent mylatestudent){
        System.out.println(mylatestudent);

        int queryMylate = userMapper.queryMylate("%"+mylatestudent.getSid()+"%", mylatestudent.getTid());
        int pageStart=(mylatestudent.getPageNum()-1)*mylatestudent.getPageSize();
        List<LateChuxing> lateChuxings = userMapper.queryMystudentchuxing("%"+mylatestudent.getSid()+"%", mylatestudent.getTid(), pageStart, mylatestudent.getPageSize());

        HashMap<String, Object> res = new HashMap<>();
        res.put("queryMylate",queryMylate);
        res.put("data",lateChuxings);
        String s = JSON.toJSONString(res);
        return s;
    }

    //查询班上学生晚归情况
    @RequestMapping("/mylatestudent")
    public String getlatestudent(Mylate late){
//        System.out.println(late);
        List<MylatechuxingFI> mylateFI = userMapper.getMylateFI(late.getTid(), late.getFtime(), late.getLtime());
//        for (MylatechuxingFI mylatechuxingFI : mylateFI) {
//            System.out.println(mylatechuxingFI);
//        }
        List<Mylatechuxing> mylatechuxings = new ArrayList<>();

        for(int i=0;i<mylateFI.size();i++){
            Student fs = userMapper.getMylateFS(mylateFI.get(i).getSid());
//            System.out.println(fs);


            Mylatechuxing mychuxing = new Mylatechuxing();
            mychuxing.setSid(mylateFI.get(i).getSid());
            mychuxing.setSname(fs.getSname());
            mychuxing.setSphone(fs.getSphone());
            mychuxing.setSnumber(fs.getSnumber());
            mychuxing.setHid(fs.getHid());
            mychuxing.setHname(fs.getHname());
            mychuxing.setHtime(mylateFI.get(i).getHtime());
            mychuxing.setHstate(mylateFI.get(i).getHstate());

            mylatechuxings.add(i,mychuxing);
        }

        HashMap<String, Object> res = new HashMap<>();
        res.put("data",mylatechuxings);
        String s = JSON.toJSONString(res);
        return s;
    }
}
