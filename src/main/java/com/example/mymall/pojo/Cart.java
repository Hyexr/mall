package com.example.mymall.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.pojo
 * @ClassName : Cart.java
 * @createTime : 2023/7/3 15:57
 */
@Data
public class Cart implements Serializable {

    private Integer productId;

    private Integer quantity;

    private Boolean productSelected;

    private static final long serialVersionUID = 1L;

    public Cart() {
    }
    public Cart(Integer id, Integer quantity, Boolean selected) {
        this.productId = id;
        this.quantity = quantity;
        this.productSelected = selected;
    }
}
