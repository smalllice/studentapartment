package com.lb.studentapartment.controller;

import com.alibaba.fastjson.JSON;
import com.lb.studentapartment.StudentapartmentApplication;
import com.lb.studentapartment.bean.*;
import com.lb.studentapartment.dao.UserMapper;
import com.lb.studentapartment.util.UartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 标题：类名称:AdminController
 * 说明：描述一下类的作用TODO
 * 时间：2022/5/6 18:19
 * 作者 @author BGG
 */
@RestController
public class MyorderController {
    @Autowired
    private UserMapper userMapper;

    //管理员查询所有学生信息
    @RequestMapping("/getallstudent")
    public String getStudentList(QueryInfo queryInfo){
        int studentCounts = userMapper.getStudentCounts("%" + queryInfo.getQuery() + "%");
        int pageStart = (queryInfo.getPageNum() - 1) * queryInfo.getPageSize();
        List<Student> students = userMapper.getallStudent("%" + queryInfo.getQuery() + "%", pageStart, queryInfo.getPageSize());
        HashMap<String, Object> res = new HashMap<>();
        System.out.println(pageStart);
        res.put("studentCounts",studentCounts);
        res.put("data",students);
        String res_string = JSON.toJSONString(res);
        return  res_string;
    }

    //管理员查询晚归学生
    @RequestMapping("/querylatestudent")
    public String querylateStudent(Querylate querylate){
        int lateCounts = userMapper.lateStudent(querylate.getSid());
        System.out.println(querylate);
        List<LateChuxing> lateChuxings = userMapper.getlateStudent(querylate.getSid(),querylate.getFhtime(),querylate.getLhtime());
        HashMap<String, Object> res = new HashMap<>();
        res.put("lateCounts",lateCounts);
        res.put("data",lateChuxings);
        String res_string = JSON.toJSONString(res);
        for (LateChuxing lateChuxing : lateChuxings) {
            System.out.println(lateChuxing);
        }
        return res_string;
    }

    //修改老师账号信息
    @RequestMapping("/updateteacher")
    public String UpdateTeacher(Teacher teacher){
        int i=0;
        String flag = "error";
        i = userMapper.updateTeacher(teacher);
        if(i!=0){
            flag="ok";
        }
        HashMap<String, Object> res = new HashMap<>();
        res.put("flag",flag);
        String res_string = JSON.toJSONString(res);
        return res_string;
    }

    //修改学生账号信息
    @RequestMapping("/updatestudent")
    public String UpdateStudent(Student student){
        int i=0;
        String flag = "error";
        i = userMapper.updateStudent(student);
        if(i!=0){
            flag="ok";
        }
        HashMap<String, Object> res = new HashMap<>();
        res.put("flag",flag);
        String res_string = JSON.toJSONString(res);
        return res_string;
    }


    //打开门禁
    @RequestMapping("/open")
    @ResponseBody
    public String Open(){
        String flag = "error";
        System.out.println("开门");
        int a=0;
        try {
            UartConfig.sendToPort(StudentapartmentApplication.getSerialPort(),'3');
            a=1;
        }catch (Exception e){
            a=0;
        }
        if(a!=0){
            flag="ok";}

        HashMap<String, Object> res = new HashMap<>();
        res.put("flag",flag);
        String res_string = JSON.toJSONString(res);
        return res_string;
    }

    //关门
    @RequestMapping("/closemenjin")
    @ResponseBody
    public String Closemenjin(){
        String flag = "error";
        System.out.println("关门");
        int a=0;
        try {
            UartConfig.sendToPort(StudentapartmentApplication.getSerialPort(),'1');
            a=1;
        }catch (Exception e){
            a=0;
        }
        if(a!=0){
            flag="ok";}

        HashMap<String, Object> res = new HashMap<>();
        res.put("flag",flag);
        String res_string = JSON.toJSONString(res);
        return res_string;
    }



}
