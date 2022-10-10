package com.lb.studentapartment.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标题：类名称:Student
 * 说明：描述一下类的作用TODO
 * 时间：2022/4/28 16:09
 * 作者 @author BGG
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    /*
    学生账户id
     */
    private String sid;

    /*
    学生账户密码
     */
    private String spwd;

    /*
    学生姓名
     */
    private String sname;
    /*
    学生所属老师的id
     */
    private String tid;
    /*
    学生当前状态
     */
    private int state;
    /*
        学生电话
    */
    private String sphone;
    /*
        学生卡的卡号
    */
    private String snumber;
    /*
        学生所在公寓id
    */
    private String hid;
    /*
        学生所在公寓的名字
    */
    private String hname;
    //权限
    private int permissions;
}
