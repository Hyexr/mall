package com.example.mymall.service;

import com.example.mymall.form.ShippingForm;
import com.example.mymall.vo.ResponseVo;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.service
 * @ClassName : IShippingService.java
 * @createTime : 2023/7/4 10:58
 */
public interface IShippingService {

    ResponseVo<Map<String, Integer>> add(Integer uid, ShippingForm form);

    ResponseVo delete(Integer uid, Integer shippingId);

    ResponseVo update(Integer uid, Integer shippingId, ShippingForm form);

    ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize);


}
