package com.example.mymall.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.vo
 * @ClassName : CartProductVo.java
 * @createTime : 2023/7/1 16:20
 */
@Data
public class CartProductVo {
    private Integer productId;

    private Integer quantity;

    private String productName;

    private String productSubtitle;

    private String productMainImage;

    private BigDecimal productPrice;

    private Integer productStatus;

    private BigDecimal productTotalPrice;

    private Integer productStock;

    private Boolean productSelected;

    public CartProductVo() {
    }
    public CartProductVo(Integer productId, Integer quantity, String productName,
                         String productSubtitle, String productMainImage, BigDecimal productPrice,
                         Integer productStatus, BigDecimal productTotalPrice, Integer productStock,
                         Boolean productSelected) {
        this.productId = productId;
        this.quantity = quantity;
        this.productName = productName;
        this.productSubtitle = productSubtitle;
        this.productMainImage = productMainImage;
        this.productPrice = productPrice;
        this.productStatus = productStatus;
        this.productTotalPrice = productTotalPrice;
        this.productStock = productStock;
        this.productSelected = productSelected;
    }
}
