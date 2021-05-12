package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckItem;

import java.util.List;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/5/8 21:46
 */
public interface CheckItemDao {
    /**
     * 添加检查项
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 分页查询检查项
     * @param queryString
     * @return
     */
    Page<CheckItem> findByCondition(String queryString);

    /**
     * 删除检查项
     * @param id
     */
    void deleteById(int id);

    /**
     * 删除检查项前查看是否在检查组中被使用
     * @param id
     * @return
     */
    int findCountByCheckItemId(int id);

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
