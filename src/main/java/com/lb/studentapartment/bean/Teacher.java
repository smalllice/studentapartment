package com.lb.studentapartment.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标题：类名称:Teacher
 * 说明：描述一下类的作用TODO
 * 时间：2022/4/28 16:18
 * 作者 @author BGG
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    /*
        老师id
    */
    private String tid;
    /*
        老师密码
    */
    private String tpwd;
    /*
        老师名字
    */
    private String tname;
    /*
        老师电话
    */
    private String tphone;
    //权限
    private int permissions;

}
