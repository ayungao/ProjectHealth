package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.HealthException;
import com.itheima.health.pojo.CheckGroup;

import java.util.List;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/5/12 20:54
 */
public interface CheckGroupService {
    /**
     * 新增检查组
     * @param checkGroup
     * @param checkitemIds
     */
    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    /**
     * 分页查询检查组
     * @param queryPageBean
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 通过id回显检查组信息
     * @param id
     * @return
     */
    CheckGroup findById(int id);

    /**
     * 通过检查组id查询包含的检查项
     * @param id
     * @return
     */
    List<Integer> findCheckItemIdsByCheckGroupId(int id);

    /**
     * 编辑检查组
     * @param checkGroup
     * @param checkitemIds
     */
    void update(CheckGroup checkGroup, Integer[] checkitemIds);

    /**
     * 根据id删除检查组
     * @param id
     */
    void deleteById(int id) throws HealthException;

    /**
     * 查询所有检查组
     * @return
     */
    List<CheckGroup> findAll();

}
