package com.example.mymall.dao;

import com.example.mymall.pojo.Order;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order row);

    int insertSelective(Order row);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order row);

    int updateByPrimaryKey(Order row);

    List<Order> selectByUid(Integer uid);

    Order selectByOrderNo(Long orderNo);
}