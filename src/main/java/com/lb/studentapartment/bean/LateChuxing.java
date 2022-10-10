package com.lb.studentapartment.bean;

import lombok.Data;

import java.util.Date;

/**
 * 标题：类名称:LateChuxing
 * 说明：描述一下类的作用TODO
 * 时间：2022/5/7 16:43
 * 作者 @author BGG
 */
@Data
public class LateChuxing {
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
