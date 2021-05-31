package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/5/18 4:10
 */
public interface SetmealDao {
    /**
     * 添加套餐
     *
     * @param setmeal
     */
    void add(Setmeal setmeal);

    /**
     * 添加套餐和检查组的关系
     *
     * @param id
     * @param checkgroupId
     */
    void addSetmealCheckGroup(@Param("id") Integer id, @Param("checkgroupId") Integer checkgroupId);

    /**
     * 分页查询套餐
     * @param queryString
     * @return
     */
    Page<Setmeal> findPage(String queryString);

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
     */
    void update(Setmeal setmeal);

    /**
     * 删除原套餐和检查组的关联
     * @param id
     */
    void deleteSetmealCheckGroup(Integer id);

    /**
     * 判断是否被订单使用
     * @param id
     * @return
     */
    int findOrderCountBySetmealId(int id);

    /**
     * 删除套餐
     * @param id
     */
    void deleteById(int id);

    /**
     * 查出数据库中的所有图片
     * @return
     */
    List<String> findImgs();

    /**
     * 查询所有套餐
     * @return
     */
    List<Setmeal> findAll();

    /**
     * 套餐详情
     * @param id
     * @return
     */
    Setmeal findDetailById(int id);
}
