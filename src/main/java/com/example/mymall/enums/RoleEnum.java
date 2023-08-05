package com.example.mymall.enums;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.enums
 * @ClassName : Role.java
 * @createTime : 2023/6/19 11:00
 */
@Getter
public enum RoleEnum {
    ADMIN(0),
    CUSTOMER(1);
    final Integer code;

    RoleEnum(int i) {
        this.code = i;
    }
}
