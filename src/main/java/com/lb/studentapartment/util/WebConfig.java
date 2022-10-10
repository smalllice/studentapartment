package com.lb.studentapartment.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 标题：类名称:WebConfig
 * 说明：描述一下类的作用TODO
 * 时间：2022/4/27 13:14
 * 作者 @author BGG
 */
//全局配置类
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    //配置跨域请求

    /**
     *1.域访问路径
     * 2.请求来源
     * 3.方法
     * 4.允许携带
     * 5.最大响应时间
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8081")
                .allowedMethods("GET","POST","PUT","DELETE")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
