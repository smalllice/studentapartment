package com.lb.studentapartment.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标题：类名称:User
 * 说明：接收前端登录时传入的账户密码
 * 时间：2022/4/28 16:30
 * 作者 @author BGG
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private String password;
}
