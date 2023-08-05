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
 * @ClassName : CartAddForm.java
 * @createTime : 2023/7/1 16:24
 */
@Data
public class CartAddForm {
    @NotNull
    private Integer productId;

    private Boolean selected = true;
}
