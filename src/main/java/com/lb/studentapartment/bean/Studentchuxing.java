package com.lb.studentapartment.bean;

import lombok.Data;

/**
 * 标题：类名称:Studentchuxing
 * 说明：描述一下类的作用TODO
 * 时间：2022/5/12 14:17
 * 作者 @author BGG
 */
@Data
public class Studentchuxing {
    //学号
    private String sid;
    //姓名
    private String sname;
    //电话
    private String sphone;
    //卡号
    private String snumber;
    //公寓号
    private String hid;
    //公寓名
    private String hname;
    //晚归时间
    private String htime;
    //公寓状态
    private int hstate;
}
