package com.itheima.health.service;

import com.itheima.health.pojo.CheckGroup;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/5/12 20:54
 */
public interface CheckGroupService {
    void add(CheckGroup checkGroup, Integer[] checkitemIds);
}
