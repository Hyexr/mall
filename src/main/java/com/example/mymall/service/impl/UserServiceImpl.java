package com.example.mymall.service.impl;

import com.example.mymall.dao.UserMapper;
import com.example.mymall.enums.ResponseEnum;
import com.example.mymall.enums.RoleEnum;
import com.example.mymall.pojo.User;
import com.example.mymall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import com.example.mymall.service.IUserService;

import java.nio.charset.StandardCharsets;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : service.impl
 * @ClassName : UserServiceImpl.java
 * @createTime : 2023/6/17 11:06
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public ResponseVo<User> register(User user){
        //名字不重复
        int countByUsername = userMapper.countByUsername(user.getUsername());
        if(countByUsername > 0) {
//            throw new RuntimeException("已注册");
            return ResponseVo.error(ResponseEnum.USERNAME_EXIST);
        }

        //email 不重复
        int countByEmail = userMapper.countByEmail(user.getEmail());
        if(countByEmail > 0) {
//            throw new RuntimeException("已注册");
            return ResponseVo.error(ResponseEnum.EMAIL_EXIST);
        }
        user.setRole(RoleEnum.CUSTOMER.getCode());
        //md5
        String s = DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8));
        user.setPassword(s);

        //写入
        int resultCount = userMapper.insertSelective(user);
        if(resultCount == 0) throw new RuntimeException("注册失败");
        user.setPassword("");
        return ResponseVo.success(user);

    }

    @Override
    public ResponseVo<User> login(String username, String password){
        User user = userMapper.selectByUsername(username);
        if(user == null) {
            //用户名不存在
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        //忽略了大小写
        if(!user.getPassword().equalsIgnoreCase(
                DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)))){
            //密码错误
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        user.setPassword("");
        return ResponseVo.success(user);
    }

}
