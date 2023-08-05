package com.example.mymall.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.controller
 * @ClassName : HelloController.java
 * @createTime : 2023/5/26 10:25
 */
@RestController
@Slf4j
public class HelloController {

    @RequestMapping("/hello")
    public String handle(){
        return "Hello, Spring Boot 3!";
    }

}
