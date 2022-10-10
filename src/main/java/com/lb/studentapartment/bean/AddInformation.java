package com.lb.studentapartment.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标题：类名称:AddInformation
 * 说明：描述一下类的作用TODO
 * 时间：2022/5/31 22:01
 * 作者 @author BGG
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddInformation {
    private String number;
    private String tid;
    private String hid;
    private String hname;
    private String sid;
    private String htime;
    private int hsate;
}
