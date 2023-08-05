package com.example.mymall.controller;

import com.example.mymall.common.BaseContext;
import com.example.mymall.form.ShippingForm;
import com.example.mymall.service.IShippingService;
import com.example.mymall.vo.ResponseVo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.controller
 * @ClassName : ShippingController.java
 * @createTime : 2023/7/30 19:56
 */
@RestController
@RequestMapping("/shipping")
public class ShippingController {

    @Autowired
    private IShippingService shippingService;

    @PostMapping("/add")
    public ResponseVo<Map<String, Integer>> add(@Valid @RequestBody ShippingForm form){
        return shippingService.add(BaseContext.getCurrentUser().getId(), form);
    }

    @DeleteMapping("/delete/{shippingId}")
    public ResponseVo delete(@PathVariable Integer shippingId){
        return shippingService.delete(BaseContext.getCurrentUser().getId(), shippingId);
    }

    @PutMapping("/update/{shippingId}")
    public ResponseVo update(@PathVariable Integer shippingId,
                             @Valid @RequestBody ShippingForm form){
        return shippingService.update(BaseContext.getCurrentUser().getId(), shippingId, form);
    }

    @GetMapping("/list")
    public ResponseVo list(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        return shippingService.list(BaseContext.getCurrentUser().getId(), pageNum, pageSize);
    }
}
