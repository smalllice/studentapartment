package com.lb.studentapartment.bean;

import lombok.Data;

/**
 * 标题：类名称:Mylatestudent
 * 说明：描述一下类的作用TODO
 * 时间：2022/5/10 12:19
 * 作者 @author BGG
 */
@Data
public class Mylatestudent {
    private String sid;//查询学生的sid
    private String tid;//当前老师的tid
    private int pageNum=1;//当前页码
    private int pageSize=1;//每页最大数
}
