package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.SetmealDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.HealthException;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/5/18 4:08
 */
@Service(interfaceClass = SetmealService.class)
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealDao setmealDao;

    /**
     * 添加套餐
     *
     * @param setmeal
     * @param checkgroupIds
     */
    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealDao.add(setmeal);
        if (checkgroupIds != null) {
            for (Integer checkgroupId : checkgroupIds) {
                setmealDao.addSetmealCheckGroup(setmeal.getId(), checkgroupId);
            }
        }
    }

    /**
     * 分页查询套餐
     *
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<Setmeal> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        if (!StringUtils.isEmpty(queryPageBean.getQueryString())) {
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }

        Page<Setmeal> setmealPage = setmealDao.findPage(queryPageBean.getQueryString());
        return new PageResult<Setmeal>(setmealPage.getTotal(), setmealPage.getResult());
    }

    /**
     * 回显套餐
     *
     * @param id
     * @return
     */
    @Override
    public Setmeal findById(int id) {
        return setmealDao.findById(id);
    }

    /**
     * 回显与套餐绑定的检查组
     *
     * @param id
     * @return
     */
    @Override
    public List<Integer> findCheckgroupIdsBySetmealId(int id) {
        return setmealDao.findCheckgroupIdsBySetmealId(id);
    }

    /**
     * 提交套餐编辑
     *
     * @param setmeal
     * @param checkgroupIds
     */
    @Override
    @Transactional
    public void update(Setmeal setmeal, Integer[] checkgroupIds) {
        // 更新套餐
        setmealDao.update(setmeal);
        // 删除旧关系
        setmealDao.deleteSetmealCheckGroup(setmeal.getId());
        // 添加新关系
        if (null != checkgroupIds) {
            for (Integer checkgroupId : checkgroupIds) {
                setmealDao.addSetmealCheckGroup(setmeal.getId(), checkgroupId);
            }
        }
    }

    /**
     * 删除套餐
     *
     * @param id
     */
    @Override
    @Transactional
    public void deleteById(int id) throws HealthException {
        // 判断是否被订单使用
        int count = setmealDao.findOrderCountBySetmealId(id);
        // 使用了则报错
        if (count > 0) {
            throw new HealthException("该套餐已经被使用了，不能删除");
        }
        // 未使用
        // 先删除套餐与检查组关系
        setmealDao.deleteSetmealCheckGroup(id);
        // 再删除套餐
        setmealDao.deleteById(id);
    }

    /**
     * 查出数据库中的所有图片
     *
     * @return
     */
    @Override
    public List<String> findImgs() {
        return setmealDao.findImgs();
    }

}
