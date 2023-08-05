package com.example.mymall.enums;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.enums
 * @ClassName : OrderStatusEnum.java
 * @createTime : 2023/7/31 20:14
 */
@Getter
public enum OrderStatusEnum {
    CANCELED(0,"已取消"),
    NO_PAY(10,"未支付"),
    PAID(20,"已支付"),
    SHIPPED(40,"已发货"),
    TRADE_SUCCESS(50,"交易成功"),
    TRADE_CLOSE(60,"交易关闭"),
    ;
    final Integer code;
    final String desc;
    OrderStatusEnum(Integer code,String desc){
        this.code = code;
        this.desc = desc;
    }
}
