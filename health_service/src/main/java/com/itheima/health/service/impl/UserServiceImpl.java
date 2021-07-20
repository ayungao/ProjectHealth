package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.UserDao;
import com.itheima.health.pojo.User;
import com.itheima.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/7/21 0:11
 */
@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 通过用户名查用户
     * @param username
     * @return
     */
    @Override
    public User UserfindByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
