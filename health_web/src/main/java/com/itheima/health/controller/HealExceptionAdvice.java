package com.itheima.health.controller;

import com.itheima.health.entity.Result;
import com.itheima.health.exception.HealthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/5/12 16:46
 */
@RestControllerAdvice
public class HealExceptionAdvice {
    /**
     *  info:  打印日志，记录流程性的内容
     *  debug: 记录一些重要的数据 id, orderId, userId
     *  error: 记录异常的堆栈信息，代替e.printStackTrace();
     *  工作中不能有System.out.println(), e.printStackTrace();
     */
    private static final Logger log = LoggerFactory.getLogger(HealthException.class);

    /**
     * 用来捕获取指定类型的异常
     * @param healthException
     * @return
     */
    @ExceptionHandler(HealthException.class)
    public Result handleHealException(HealthException healthException){
        return new Result(false,healthException.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception exception){
        return new Result(false,"发生未知错误，操作失败，请联系管理员");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Result handleAccessDeniedException(AccessDeniedException e){
        return new Result(false, "权限不足");
    }

}
