package com.example.mymall.dao;

import com.example.mymall.pojo.Shipping;
import org.apache.ibatis.annotations.Param;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Set;

public interface ShippingMapper {
    int deleteByPrimaryKey(Integer id);
    int deleteByIdAndUid(@Param("uid") Integer uid, @Param("shippingId") Integer shippingId);

    int insert(Shipping row);

    int insertSelective(Shipping row);

    Shipping selectByPrimaryKey(Integer id);
    Shipping selectByUidAndShippingId(@Param("uid") Integer uid, @Param("shippingId") Integer shippingId);
    List<Shipping> selectByUid(Integer uid);

    List<Shipping> selectByIdSet(@Param("idSet") Set<Integer> idSet);

    int updateByPrimaryKeySelective(Shipping row);

    int updateByPrimaryKey(Shipping row);
}