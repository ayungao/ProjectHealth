package com.itheima.health.controller;

import com.itheima.health.entity.Result;
import com.itheima.health.exception.HealthException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/5/12 16:46
 */
@RestControllerAdvice
public class HealExceptionAdvice {

    @ExceptionHandler(HealthException.class)
    public Result handleHealException(HealthException healthException){
        return new Result(false,healthException.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception exception){
        return new Result(false,"发生未知错误，操作失败，请联系管理员");
    }
}
