package com.example.mymall.service;

import com.example.mymall.pojo.User;
import com.example.mymall.vo.ResponseVo;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : service
 * @ClassName : IUserService.java
 * @createTime : 2023/6/17 11:03
 */
public interface IUserService {
    /**
     * 注册
     */
    ResponseVo<User> register(User user);

    /**
     * 登录
     */
    ResponseVo<User> login(String username, String password);

}
