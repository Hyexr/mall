package com.example.mymall.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.form
 * @ClassName : OrderCreateForm.java
 * @createTime : 2023/8/1 20:05
 */
@Data
public class OrderCreateForm {
    @NotNull
    private Integer shippingId;
}
