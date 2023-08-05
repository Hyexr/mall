package com.example.mymall.enums;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.enums
 * @ClassName : ProductStatus.java
 * @createTime : 2023/7/1 15:08
 */
@Getter
public enum ProductStatus {
    ON_SALE(1),
    OFF_SALE(2),
    DELETE(3),
    ;
    final Integer code;

    ProductStatus(Integer code) {
        this.code = code;
    }
}
