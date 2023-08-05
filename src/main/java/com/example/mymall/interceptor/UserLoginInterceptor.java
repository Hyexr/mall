package com.example.mymall.interceptor;

import com.example.mymall.common.BaseContext;
import com.example.mymall.common.MallConst;
import com.example.mymall.enums.ResponseEnum;
import com.example.mymall.exception.UserLoginException;
import com.example.mymall.pojo.User;
import com.example.mymall.vo.ResponseVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.config
 * @ClassName : UserLoginInterceptor.java
 * @createTime : 2023/6/22 17:04
 */
@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {
        log.info("preHandle...");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        if(user == null){
            log.info("user=null");
            throw new UserLoginException();
        }
        BaseContext.setCurrentUser(user);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        log.info("afterCompletion...");
        BaseContext.removeCurrentUser();
    }
}
