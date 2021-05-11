package com.itheima.health.dao;

import com.itheima.health.pojo.CheckItem;

import java.util.List;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/5/8 21:46
 */
public interface CheckItemDao {
    List<CheckItem> findAll();
}
