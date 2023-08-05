package com.example.mymall.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.form
 * @ClassName : UserLoginForm.java
 * @createTime : 2023/6/22 16:07
 */
@Data
public class UserLoginForm {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
