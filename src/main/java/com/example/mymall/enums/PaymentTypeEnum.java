package com.example.mymall.enums;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.enums
 * @ClassName : PaymentType.java
 * @createTime : 2023/7/31 20:11
 */
@Getter
public enum PaymentTypeEnum {
    PAY_ONLINE(1),
    ;
    final Integer code;
    PaymentTypeEnum(Integer code){
        this.code = code;
    }
}
