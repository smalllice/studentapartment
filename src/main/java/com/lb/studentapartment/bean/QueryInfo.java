package com.lb.studentapartment.bean;

import lombok.Data;

/**
 * 标题：类名称:QuertInfo
 * 说明：描述一下类的作用TODO
 * 时间：2022/5/6 18:20
 * 作者 @author BGG
 */
@Data
public class QueryInfo {
    private String query;//查询信息 sid
    private int pageNum=1;//当前页码
    private int pageSize=1;//每页最大数
}
