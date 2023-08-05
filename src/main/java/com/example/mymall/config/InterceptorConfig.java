package com.example.mymall.config;

import com.example.mymall.interceptor.UserLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.config
 * @ClassName : InterceptorConfig.java
 * @createTime : 2023/6/22 17:10
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserLoginInterceptor())
                //拦截所有请求
                .addPathPatterns("/**")
                //排除静态资源
                .excludePathPatterns("/user/login","/user/register");
    }
}
