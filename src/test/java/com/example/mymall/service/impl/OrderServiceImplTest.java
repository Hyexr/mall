package com.example.mymall.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.example.mymall.service.IOrderService;
import com.example.mymall.vo.OrderVo;
import com.example.mymall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.service.impl
 * @ClassName : com.example.mymall.service.impl.OrderServiceImpl.java
 * @createTime : 2023/7/31 21:02
 */
@SpringBootTest
@Slf4j
class OrderServiceImplTest {
    @Autowired
    private IOrderService orderService;

    private Integer uid = 1;
    private Integer shippingId = 4;
    @Test
    void create() {
        ResponseVo<OrderVo> responseVo = orderService.create(uid, shippingId);
        log.info("result={}", JSON.toJSONString(responseVo, JSONWriter.Feature.PrettyFormat));
        assertEquals(0, responseVo.getStatus());
    }
}