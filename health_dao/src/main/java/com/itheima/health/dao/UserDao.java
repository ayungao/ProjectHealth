package com.itheima.health.dao;

import com.itheima.health.pojo.User;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/7/21 0:14
 */
public interface UserDao {
    /**
     * 通过用户名查用户
     * @param username
     * @return
     */
    User findByUsername(String username);
}
