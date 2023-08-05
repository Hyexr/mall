package com.example.mymall.service;

import com.example.mymall.form.CartAddForm;
import com.example.mymall.form.CartUpdateForm;
import com.example.mymall.pojo.Cart;
import com.example.mymall.vo.CartVo;
import com.example.mymall.vo.ResponseVo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.service
 * @ClassName : ICartService.java
 * @createTime : 2023/7/3 14:48
 */
public interface ICartService {
    ResponseVo<CartVo> add(Integer uid, CartAddForm cartAddForm);

    ResponseVo<CartVo> list(Integer uid);

    ResponseVo<CartVo> update(Integer uid, Integer productId, CartUpdateForm cartUpdateForm);

    ResponseVo<CartVo> delete(Integer uid, Integer productId);

    ResponseVo<CartVo> selectAll(Integer uid);

    ResponseVo<CartVo> unSelectAll(Integer uid);

    ResponseVo<Integer> sum(Integer uid);

    List<Cart> listForCart(Integer uid);
}
