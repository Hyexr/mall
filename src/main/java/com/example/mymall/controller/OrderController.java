package com.example.mymall.controller;

import com.example.mymall.common.BaseContext;
import com.example.mymall.form.OrderCreateForm;
import com.example.mymall.pojo.User;
import com.example.mymall.service.IOrderService;
import com.example.mymall.vo.OrderVo;
import com.example.mymall.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.controller
 * @ClassName : OrderController.java
 * @createTime : 2023/8/1 20:04
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @PostMapping("/create")
    public ResponseVo<OrderVo> create(@Valid @RequestBody OrderCreateForm form) {
        User user = BaseContext.getCurrentUser();
        return orderService.create(user.getId(), form.getShippingId());
    }
    @GetMapping("/list")
    public ResponseVo<PageInfo> list(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                     @RequestParam(required = false,defaultValue = "10") Integer pageSize) {
        User user = BaseContext.getCurrentUser();
        return orderService.list(user.getId(),pageNum,pageSize);
    }
    @GetMapping("/detail/{orderNo}")
    public ResponseVo<OrderVo> detail(@PathVariable Long orderNo) {
        User user = BaseContext.getCurrentUser();
        return orderService.detail(user.getId(),orderNo);
    }
    @PutMapping("/cancel/{orderNo}")
    public ResponseVo cancel(@PathVariable Long orderNo) {
        User user = BaseContext.getCurrentUser();
        return orderService.cancel(user.getId(),orderNo);
    }
}
