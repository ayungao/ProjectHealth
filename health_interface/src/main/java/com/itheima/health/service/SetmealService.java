package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.HealthException;
import com.itheima.health.pojo.Setmeal;

import java.util.List;


/**
 * @description:
 * @author: AyuNGao
 * @date: 21/5/18 4:07
 */
public interface SetmealService {
    /**
     * 添加套餐
     * @param setmeal
     * @param checkgroupIds
     */
    void add(Setmeal setmeal, Integer[] checkgroupIds);

    /**
     * 分页查询套餐
     * @param queryPageBean
     * @return
     */
    PageResult<Setmeal> findPage(QueryPageBean queryPageBean);

    /**
     * 回显套餐
     * @param id
     * @return
     */
    Setmeal findById(int id);

    /**
     * 回显与套餐绑定的检查组
     * @param id
     * @return
     */
    List<Integer> findCheckgroupIdsBySetmealId(int id);

    /**
     * 提交套餐编辑
     * @param setmeal
     * @param checkgroupIds
     */
    void update(Setmeal setmeal, Integer[] checkgroupIds);

    /**
     * 删除套餐
     * @param id
     */
    void deleteById(int id) throws HealthException;

    /**
     * 查出数据库中的所有图片
     * @return
     */
    List<String> findImgs();
}
