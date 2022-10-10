package com.lb.studentapartment.dao;

import com.lb.studentapartment.bean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 标题：类名称:UserMapper
 * 说明：描述一下类的作用TODO
 * 时间：2022/4/28 20:54
 * 作者 @author BGG
 */
@Mapper
@Repository
public interface UserMapper {
    //查出列表中全部的老师
    List<Teacher> getallTeacher();

    //查询全部的学生信息
    List<Student> getallStudent(@Param("sid")String sid,@Param("pageStart") int pageStart,@Param("pageSize")int pageSize);
    //全部学生信息的条数
    int getStudentCounts(@Param("sid") String sid);


    //查询晚归学生的出行记录
    int lateStudent(@Param("sid") String sid);
    List<LateChuxing> getlateStudent(@Param("sid") String sid,@Param("fhtime") String fhtime,@Param("lhtime")String lhtime);

    //登录时查是否在老师表
    Teacher getTeacherByMassage(@Param("tid") String tid,@Param("tpwd") String tpwd);
    //登录时查看是否在学生表
    Student getStudentByMassage(@Param("sid") String sid,@Param("spwd")String spwd);
    //登录时查看是否在管理员表
    Admin getAdminByMassage(@Param("aid") String sid,@Param("apwd")String spwd);

    //查询班上学生
    List<Student> getMystudent(@Param("sid")String sid,@Param("tid")String tid,@Param("pageStart") int pageStart,@Param("pageSize")int pageSize);
    int getMystudentCounts(@Param("sid")String sid,@Param("tid")String tid);

    //查询班上学生出行的记录
    int queryMylate(@Param("sid") String sid,@Param("tid")String tid);
    List<LateChuxing> queryMystudentchuxing(@Param("sid") String sid,@Param("tid")String tid,@Param("pageStart") int pageStart,@Param("pageSize")int pageSize);

    //查询班上晚归情况
    List<MylatechuxingFI> getMylateFI(@Param("tid")String tid,@Param("ftime")String ftime,@Param("ltime")String ltime);
    Student getMylateFS(@Param("sid")String sid);

    //查看自己的出行记录
    List<StudentchuxingFI> getPersonchuxing(@Param("sid")String sid,@Param("ftime")String ftime,@Param("ltime")String ltime);
    Student getPerson(@Param("sid")String sid);

    //注册老师
    int addTecaher(Teacher teacher);
    //注册学上
    int addStudent(Student student);


    //修改老师账户信息
    int updateTeacher(Teacher teacher);

    //修改学生账号信息
    int updateStudent(Student student);


    //通过卡号查询学生信息
    Student getStudentBycard(@Param("snumber")String snumber);
    //插入出行记录
    int addinfor(AddInformation addInformation);
    int addinfor2(@Param("number")String number,@Param("tid")String tid,@Param("hid")String hid,@Param("hname")String hname,@Param("sid") String sid,@Param("htime")String htime,@Param("hstate")int hstate);

    //根据学生卡号更改当前学生处于公寓的状态
    int updateStudentState(@Param("snumber")String snumber,@Param("state") int state);
}
