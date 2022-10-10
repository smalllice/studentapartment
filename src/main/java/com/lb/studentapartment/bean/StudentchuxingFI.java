package com.lb.studentapartment.bean;

import lombok.Data;

/**
 * 标题：类名称:StudentchuxingFI
 * 说明：个人出行信息查询时，从出行记录表中查出的数据
 * 时间：2022/5/12 14:00
 * 作者 @author BGG
 */
@Data
public class StudentchuxingFI {
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
