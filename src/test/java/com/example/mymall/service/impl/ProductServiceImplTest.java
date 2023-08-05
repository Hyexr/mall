package com.example.mymall.service.impl;

import com.example.mymall.enums.ResponseEnum;
import com.example.mymall.service.IProductService;
import com.example.mymall.vo.ProductDetailVo;
import com.example.mymall.vo.ProductVo;
import com.example.mymall.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.service.impl
 * @ClassName : com.example.mymall.service.impl.ProductServiceImpl.java
 * @createTime : 2023/7/1 10:06
 */
@SpringBootTest
class ProductServiceImplTest {
    @Autowired
    private IProductService productService;

    @Test
    void list() {
        ResponseVo<PageInfo<ProductVo>> listResponseVo = productService.list(null, 1, 1);
        assertEquals(ResponseEnum.SUCCESS.getCode(), listResponseVo.getStatus());
    }

    @Test
    void detail() {
        ResponseVo<ProductDetailVo> detail = productService.detail(26);
        assertEquals(ResponseEnum.SUCCESS.getCode(), detail.getStatus());
    }
}