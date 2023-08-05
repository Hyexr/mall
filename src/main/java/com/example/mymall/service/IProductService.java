package com.example.mymall.service;

import com.example.mymall.vo.ProductDetailVo;
import com.example.mymall.vo.ProductVo;
import com.example.mymall.vo.ResponseVo;
import com.github.pagehelper.PageInfo;


import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.service
 * @ClassName : IProductService.java
 * @createTime : 2023/7/1 9:52
 */
public interface IProductService {
    ResponseVo<PageInfo<ProductVo>> list(Integer categoryId, Integer pageNum, Integer pageSize);

    ResponseVo<ProductDetailVo> detail(Integer productId);
}
