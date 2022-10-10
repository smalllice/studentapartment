package com.lb.studentapartment.controller;

import com.alibaba.fastjson.JSON;
import com.lb.studentapartment.StudentapartmentApplication;
import com.lb.studentapartment.bean.Admin;
import com.lb.studentapartment.bean.Student;
import com.lb.studentapartment.bean.Teacher;
import com.lb.studentapartment.bean.User;
import com.lb.studentapartment.dao.UserMapper;
import com.lb.studentapartment.util.UartConfig;
import com.lb.studentapartment.util.UartServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 标题：类名称:LoginController
 * 说明：描述一下类的作用TODO
 * 时间：2022/4/28 16:23
 * 作者 @author BGG
 */
@RestController
public class LoginController {

   @Autowired
   private UserMapper userMapper;

    //登录验证
    @RequestMapping("/login")
    public String login(@RequestBody User user){
        //System.out.println("user"+user);
        String flag="error";
        int permissions=0;
        Teacher teacherByMassage = userMapper.getTeacherByMassage(user.getUsername(), user.getPassword());
        Student studentByMassage = userMapper.getStudentByMassage(user.getUsername(), user.getPassword());
        Admin adminByMassage = userMapper.getAdminByMassage(user.getUsername(), user.getPassword());
        HashMap<String, Object> res = new HashMap<>();
        if(adminByMassage!=null){
            flag="ok";
            permissions=adminByMassage.getPermissions();
        }else if(teacherByMassage!=null){
            flag="ok";
            permissions=teacherByMassage.getPermissions();
        }else if(studentByMassage!=null){
            flag="ok";
            permissions=studentByMassage.getPermissions();
        }
        res.put("flag",flag);
        if(permissions==0){
            res.put("user",adminByMassage);
        }else if(permissions==1){
            res.put("user",teacherByMassage);
        }else if(permissions==2){
            res.put("user",studentByMassage);
        }
        res.put("permissions",permissions);

        String res_json = JSON.toJSONString(res);
        return res_json;
    }

    //老师注册
    @RequestMapping("/registerTeacher")
    public String RegisterTeacher(Teacher teacher){
        int i=0;
        String flag="error";
        System.out.println(teacher);

        Teacher teacher1 = new Teacher();
        teacher1.setPermissions(1);
        teacher1.setTid(teacher.getTid());
        teacher1.setTpwd(teacher.getTpwd());
        teacher1.setTname(teacher.getTname());
        teacher1.setTphone(teacher.getTphone());
        i = userMapper.addTecaher(teacher1);
        //System.out.println(i);
        if(i==1){
            flag="ok";
        }else {
            flag="error";
        }

        HashMap<String, Object> res = new HashMap<>();

        res.put("flag",flag);
        String res_json = JSON.toJSONString(res);
        return res_json;
    }

    //学生注册
    @RequestMapping("registerStudent")
    public String RegisterStudent(Student student){
        int i=0;
        String flag="error";
        System.out.println(student);

        Student student1 = new Student();
        student1.setPermissions(2);
        student1.setState(0);
        student1.setSid(student.getSid());
        student1.setSpwd(student.getSpwd());
        student1.setSname(student.getSname());
        student1.setTid(student.getTid());
        student1.setSphone(student.getSphone());
        student1.setHid(student.getHid());
        student1.setHname(student.getHname());
        student1.setSnumber(student.getSnumber());
         i = userMapper.addStudent(student1);
        if(i==1){
            flag="ok";
        }else {
            flag="error";
        }

        HashMap<String, Object> res = new HashMap<>();

        res.put("flag",flag);
        String res_json = JSON.toJSONString(res);
        return res_json;
    }

    //获取卡号
    @RequestMapping("/readID")
    public String readNum(){
        String flag = "error";
        System.out.println("读取卡号");
        String ID="";
        HashMap<String, Object> res = new HashMap<>();
        int a=0;
      //  UartConfig.sendToPort(StudentapartmentApplication.getSerialPort(),'1');
        UartConfig.sendToPort(StudentapartmentApplication.getSerialPort(),'4');
        for(;;)
        {
            ID= UartServer.getID();
           // UartConfig.sendToPort(StudentapartmentApplication.getSerialPort(),'1');
         //   UartConfig.sendToPort(StudentapartmentApplication.getSerialPort(),'4');
            if(ID!=null){
                System.out.println("ID:"+ID);
                break;
            }
        }
        if(ID!=null){
            flag="ok";
        }else {
            flag="error";
        }
        UartServer.setID("");
        UartConfig.sendToPort(StudentapartmentApplication.getSerialPort(),'1');

        res.put("flag",flag);
        res.put("ID",ID);
        String res_json = JSON.toJSONString(res);
        return res_json;
    }




    @RequestMapping("/t1")
    public List<Teacher> test(){
        List<Teacher> teachers = userMapper.getallTeacher();
        System.out.println(StringUtils.isEmpty(teachers));


        Student studentByMassage = userMapper.getStudentByMassage("asda", "asdas");
        System.out.println(StringUtils.isEmpty(studentByMassage));

        return teachers;
    }
}
