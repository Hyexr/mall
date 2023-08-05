package com.example.mymall.controller;

import com.example.mymall.common.BaseContext;
import com.example.mymall.common.MallConst;
import com.example.mymall.enums.ResponseEnum;
import com.example.mymall.form.UserLoginForm;
import com.example.mymall.form.UserRegisterForm;
import com.example.mymall.pojo.User;
import com.example.mymall.service.IUserService;
import com.example.mymall.vo.ResponseVo;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.controller
 * @ClassName : UserController.java
 * @createTime : 2023/6/19 11:20
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/register")
    public ResponseVo<User> register(@Valid @RequestBody UserRegisterForm userRegisterForm){
        log.info("注册, {}", userRegisterForm.toString());
//        if(bindingResult.hasErrors()){
//            log.error("注册提交的参数有误, {} {}",
//                    bindingResult.getFieldError().getField(),
//                    bindingResult.getFieldError().getDefaultMessage());
//            return ResponseVo.error(ResponseEnum.PARAM_ERROR, bindingResult);
//        }
        User user = new User();
        BeanUtils.copyProperties(userRegisterForm, user);
        // dto
        return userService.register(user);
    }

    @PostMapping("/login")
    public ResponseVo<User> login(@Valid @RequestBody UserLoginForm userLoginForm,
                                  HttpSession session){
        log.info("登录, {}", userLoginForm.toString());
//        if(bindingResult.hasErrors()){
//            log.error("注册提交的参数有误, {} {}",
//                    bindingResult.getFieldError().getField(),
//                    bindingResult.getFieldError().getDefaultMessage());
//            return ResponseVo.error(ResponseEnum.PARAM_ERROR, bindingResult);
//        }
        ResponseVo<User> responseVo = userService.login(userLoginForm.getUsername(), userLoginForm.getPassword());
        session.setAttribute(MallConst.CURRENT_USER, responseVo.getData());
        return responseVo;
    }

    //session保存在内存里，重启后就没有了，所以要用redis,token+redis
    @GetMapping("/user/user")
    public ResponseVo<User> userInfo(){
        User user = BaseContext.getCurrentUser();
        return ResponseVo.success(user);
    }


    @PostMapping("/logout")
    public ResponseVo<User> logout(HttpSession session){
//        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
//        if(user == null){
//            return ResponseVo.error(ResponseEnum.NEED_LOGIN);
//        }
        session.removeAttribute(MallConst.CURRENT_USER);
        return ResponseVo.success();
    }
}
