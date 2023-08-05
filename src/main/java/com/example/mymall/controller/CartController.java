package com.example.mymall.controller;

import com.example.mymall.common.BaseContext;
import com.example.mymall.form.CartAddForm;
import com.example.mymall.form.CartUpdateForm;
import com.example.mymall.pojo.User;
import com.example.mymall.service.ICartService;
import com.example.mymall.vo.CartVo;
import com.example.mymall.vo.ResponseVo;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.controller
 * @ClassName : CartController.java
 * @createTime : 2023/7/1 16:26
 */
@RestController
@Slf4j
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private ICartService cartService;

    @PostMapping
    public ResponseVo<CartVo> add(@RequestBody @Valid CartAddForm cartAddForm) {
        User user = BaseContext.getCurrentUser();

        return cartService.add(user.getId(), cartAddForm);
    }

    @GetMapping
    public ResponseVo<CartVo> list() {
        User user = BaseContext.getCurrentUser();
        return cartService.list(user.getId());
    }

    @PutMapping("/{productId}")
    public ResponseVo<CartVo> update(@PathVariable Integer productId,
                                     @RequestBody @Valid CartUpdateForm cartUpdateForm) {
        User user = BaseContext.getCurrentUser();
        return cartService.update(user.getId(), productId, cartUpdateForm);
    }

    @DeleteMapping("/{productId}")
    public ResponseVo<CartVo> delete(@PathVariable Integer productId) {
        User user = BaseContext.getCurrentUser();
        return cartService.delete(user.getId(), productId);
    }

    @PutMapping("/selectAll")
    public ResponseVo<CartVo> selectAll() {
        User user = BaseContext.getCurrentUser();
        return cartService.selectAll(user.getId());
    }

    @PutMapping("/unSelectAll")
    public ResponseVo<CartVo> unSelectAll() {
        User user = BaseContext.getCurrentUser();
        return cartService.unSelectAll(user.getId());
    }

    @GetMapping("/sum")
    public ResponseVo<Integer> sum() {
        User user = BaseContext.getCurrentUser();
        return cartService.sum(user.getId());
    }
}
