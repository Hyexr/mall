package com.example.mymall.common;

import com.example.mymall.pojo.User;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.common
 * @ClassName : BaseContext.java
 * @createTime : 2023/6/22 16:46
 */
public class BaseContext {
    private static final ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static void setCurrentUser(User user){
        threadLocal.set(user);
    }

    public static User getCurrentUser(){
        return threadLocal.get();
    }

    public static void removeCurrentUser(){
        threadLocal.remove();
    }
}