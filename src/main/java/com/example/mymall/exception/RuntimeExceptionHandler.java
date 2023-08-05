package com.example.mymall.exception;

import com.example.mymall.enums.ResponseEnum;
import com.example.mymall.vo.ResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 黄烨轩
 * @version : 1.0
 * @Project : mymall
 * @Package : com.example.mymall.exception
 * @ClassName : RuntimeExceptionHandler.java
 * @createTime : 2023/6/21 18:55
 */
@ControllerAdvice
@ResponseBody
public class RuntimeExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseVo handle(RuntimeException e){
        return ResponseVo.error(ResponseEnum.ERROR, e.getMessage());
    }

    @ExceptionHandler(UserLoginException.class)
    public ResponseVo userLoginHandle(){
        return ResponseVo.error(ResponseEnum.NEED_LOGIN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseVo notValidExceptionHandle(MethodArgumentNotValidException e){
        return ResponseVo.error(ResponseEnum.PARAM_ERROR, e.getBindingResult());
    }

    @ExceptionHandler(OrderCreateException.class)
    public ResponseVo orderCreateExceptionHandle(){
        return ResponseVo.error(ResponseEnum.ERROR);
    }
}
