package com.lb.studentapartment.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 标题：类名称:TestController
 * 说明：描述一下类的作用TODO
 * 时间：2022/4/28 15:03
 * 作者 @author BGG
 */
@RestController
public class TestController {
    @RequestMapping(value = "/test")
    public String test(){
        return "ok";
    }
}
