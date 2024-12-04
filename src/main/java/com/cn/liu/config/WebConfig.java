package com.cn.liu.config;

import com.cn.liu.interceptor.ParamInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加自定义拦截器，并指定拦截路径
        registry.addInterceptor(new ParamInterceptor())
                .addPathPatterns("/demo/insertUser")
                .addPathPatterns("/demo/getUsers");
    }
}
