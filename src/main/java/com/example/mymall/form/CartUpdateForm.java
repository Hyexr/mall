package com.example.mymall.form;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.form
 * @ClassName : CartUpdateForm.java
 * @createTime : 2023/7/3 18:34
 */
@Data
public class CartUpdateForm {
    private Integer quantity;

    private Boolean selected;
}
