package com.itheima.health.controller;

import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/7/23 22:36
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 获取登录的用户名
     *
     * @return
     */
    @RequestMapping("/getLoginUsername")
    public Result getLoginUsername() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return new Result(true, MessageConstant.GET_USERNAME_SUCCESS, name);
    }

    @RequestMapping("/loginSuccess")
    public Result loginSuccess() {
        return new Result(true, "登陆成功");
    }

    @RequestMapping("/loginFail")
    public Result loginFail() {
        return new Result(false, "登陆失败");
    }
}
