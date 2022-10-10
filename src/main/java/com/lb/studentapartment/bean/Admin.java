package com.lb.studentapartment.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标题：类名称:Admin
 * 说明：描述一下类的作用TODO
 * 时间：2022/4/28 21:25
 * 作者 @author BGG
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    //管理员账号
    private String aid;
    //管理员密码
    private String apwd;
    //管理员电话
    private String aphone;
    //权限
    private int permissions;
}
