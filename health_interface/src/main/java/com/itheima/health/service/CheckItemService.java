package com.itheima.health.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.pojo.CheckItem;

import java.util.List;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/5/8 21:38
 */
public interface CheckItemService {
    List<CheckItem> findAll();
}
