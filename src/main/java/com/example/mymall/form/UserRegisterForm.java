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
 * @ClassName : UserForm.java
 * @createTime : 2023/6/21 18:12
 */
@Data
public class UserRegisterForm {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
}
