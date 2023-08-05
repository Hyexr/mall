package com.example.mymall.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.example.mymall.form.CartAddForm;
import com.example.mymall.form.CartUpdateForm;
import com.example.mymall.service.ICartService;
import com.example.mymall.vo.CartVo;
import com.example.mymall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.service.impl
 * @ClassName : com.example.mymall.service.impl.CartServiceImpl.java
 * @createTime : 2023/7/3 16:02
 */
@SpringBootTest
@Slf4j
class CartServiceImplTest {
    @Autowired
    private ICartService cartService;

    @Test
    void add() {
        CartAddForm cartAddForm = new CartAddForm();
        cartAddForm.setProductId(27);
        cartAddForm.setSelected(true);
        ResponseVo<CartVo> list = cartService.add(1, cartAddForm);
        log.info("list={}",JSON.toJSONString(list, JSONWriter.Feature.PrettyFormat));
    }

    @Test
    void list() {
        ResponseVo<CartVo> list = cartService.list(1);
        log.info("list={}",JSON.toJSONString(list, JSONWriter.Feature.PrettyFormat));
    }

    @Test
    void update() {
        CartUpdateForm cartUpdateForm = new CartUpdateForm();
        cartUpdateForm.setQuantity(5);
        cartUpdateForm.setSelected(false);
        ResponseVo<CartVo> list = cartService.update(1, 26, cartUpdateForm);
        log.info("list={}",JSON.toJSONString(list, JSONWriter.Feature.PrettyFormat));
    }

    @Test
    void delete() {
        ResponseVo<CartVo> list = cartService.delete(1, 26);
        log.info("list={}",JSON.toJSONString(list, JSONWriter.Feature.PrettyFormat));
    }
}