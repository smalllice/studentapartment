package com.lb.studentapartment.bean;

import lombok.Data;

/**
 * 标题：类名称:MylatechuxingFI
 * 说明：描述一下类的作用TODO
 * 时间：2022/5/10 22:17
 * 作者 @author BGG
 */
@Data
public class MylatechuxingFI {
    //记录数
    private String number;
    //公寓id
    private String hid;
    //公寓名字
    private String hname;
    //学生学号
    private String sid;
    //老师tid
    private String tid;
    //记录的时间
    private String htime;
    //记录的状态
    private int hstate;
}
