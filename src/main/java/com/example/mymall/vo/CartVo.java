package com.example.mymall.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.vo
 * @ClassName : CartVo.java
 * @createTime : 2023/7/1 16:18
 */
@Data
public class CartVo {
    private List<CartProductVo> cartProductVoList;
    private Boolean allChecked;
    private BigDecimal cartTotalPrice;
    private Integer cartTotalQuantity;
}
