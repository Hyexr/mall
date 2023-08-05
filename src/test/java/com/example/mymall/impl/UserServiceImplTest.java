package com.example.mymall.impl;


import com.example.mymall.enums.ResponseEnum;
import com.example.mymall.enums.RoleEnum;
import com.example.mymall.pojo.User;
import com.example.mymall.vo.ResponseVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.mymall.service.IUserService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : service.impl
 * @ClassName : service.impl.UserServiceImpl.java
 * @createTime : 2023/6/19 10:52
 */
@SpringBootTest
@Transactional
class UserServiceImplTest {
    public static final String USERNAME = "jack";
    public static final String PASSWORD = "123456";
    public static final String EMAIL = "jack@qq.com";
    @Autowired
    private IUserService userService;

//    @Test
    @BeforeEach
    public void register() {
        User user = new User(USERNAME, PASSWORD, EMAIL, RoleEnum.CUSTOMER.getCode());
        userService.register(user);
    }

    @Test
    public void login() {
//        register(); //用before注解
        ResponseVo<User> responseVo = userService.login(USERNAME, PASSWORD);
        assert responseVo.getStatus().equals(ResponseEnum.SUCCESS.getCode());
    }
}