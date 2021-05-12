package com.itheima.health.service;


import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.HealthException;
import com.itheima.health.pojo.CheckItem;

import java.util.List;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/5/8 21:38
 */
public interface CheckItemService {

    /**
     * 添加检查项
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 分页查询检查项
     * @param queryPageBean
     * @return
     */
    PageResult<CheckItem> findPage(QueryPageBean queryPageBean);

    /**
     * 删除检查项
     * @param id
     */
    void deleteById(int id) throws HealthException;

    /**
     * 回显检查项数据
     * @param id
     * @return
     */
    CheckItem findById(int id);

    /**
     * 编辑更新检查项
     * @param checkItem
     */
    void update(CheckItem checkItem);

    /**
     * 查询检查项
     * @return
     */
    List<CheckItem> findAll();
}
