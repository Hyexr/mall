package com.example.mymall.service.impl;

import com.example.mymall.enums.ResponseEnum;
import com.example.mymall.form.ShippingForm;
import com.example.mymall.service.IShippingService;
import com.example.mymall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.service.impl
 * @ClassName : com.example.mymall.service.impl.ShippingServiceImpl.java
 * @createTime : 2023/7/14 21:34
 */
@SpringBootTest
@Slf4j
class ShippingServiceImplTest {

    @Autowired
    private IShippingService shippingService;

    private Integer uid = 1;

    @Test
    void add() {
        ShippingForm form = new ShippingForm();
        form.setReceiverName("aaa");
        form.setReceiverAddress("beijing");
        form.setReceiverCity("beijing");
        form.setReceiverMobile("12345678910");
        form.setReceiverPhone("12345678910");
        form.setReceiverProvince("beijing");
        form.setReceiverDistrict("beijing");
        form.setReceiverZip("123456");
        ResponseVo<Map<String, Integer>> responseVo = shippingService.add(uid, form);
        log.info("result={}", responseVo);
        assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    void delete() {
        ResponseVo responseVo = shippingService.delete(uid, 7);
        log.info("result={}", responseVo);
        assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }
}