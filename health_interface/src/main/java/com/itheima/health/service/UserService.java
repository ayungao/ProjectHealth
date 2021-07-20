package com.itheima.health.service;

import com.itheima.health.pojo.User;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/7/20 23:48
 */
public interface UserService {
    /**
     * 通过用户名查用户
     * @param username
     * @return
     */
    User UserfindByUsername(String username);

}
