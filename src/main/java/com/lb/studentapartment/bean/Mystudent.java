package com.lb.studentapartment.bean;

import lombok.Data;

/**
 * 标题：类名称:Mystudent
 * 说明：描述一下类的作用TODO
 * 时间：2022/5/10 10:40
 * 作者 @author BGG
 */
@Data
public class Mystudent {
    private String sid;//查询学生的sid
    private String tid;//当前老师的tid
    private int pageNum=1;//当前页码
    private int pageSize=1;//每页最大数
}
