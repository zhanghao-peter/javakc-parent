package com.javakc.servicebase.hanler;

import com.javakc.commonutils.api.APICODE;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常格式返回
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public APICODE errorHandler(Exception e) {
        e.printStackTrace();
        return APICODE.ERROR().message("服务器异常：Exception");
    }
    @ResponseBody
    @ExceptionHandler(HctfException.class)
    public APICODE errorHandler(HctfException e) {
        e.printStackTrace();
        return APICODE.ERROR().code(e.getCode()).message(e.getMsg());
    }
}
