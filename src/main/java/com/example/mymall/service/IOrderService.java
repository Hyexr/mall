package com.example.mymall.service;

import com.example.mymall.vo.OrderVo;
import com.example.mymall.vo.ResponseVo;
import com.github.pagehelper.PageInfo;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.service
 * @ClassName : IOrderService.java
 * @createTime : 2023/7/30 20:17
 */
public interface IOrderService {

    ResponseVo<OrderVo> create(Integer uid, Integer shippingId);

    ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize);

    ResponseVo<OrderVo> detail(Integer uid, Long orderNo);

    ResponseVo cancel(Integer uid, Long orderNo);

    void paid(Long orderNo);
}
