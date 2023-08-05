package com.example.mymall.dao;

import com.example.mymall.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User row);

    int insertSelective(User row);

    User selectByPrimaryKey(Integer id);

    User selectByUsername(String username);

    int updateByPrimaryKeySelective(User row);

    int updateByPrimaryKey(User row);

    int countByUsername(String username);

    int countByEmail(String email);


}