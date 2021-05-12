package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.exception.HealthException;
import com.itheima.health.dao.CheckItemDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.service.CheckItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * @description:
 * @author: AyuNGao
 * @date: 21/5/8 21:43
 */
@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;

    /**
     * 添加检查项
     *
     * @param checkItem
     */
    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    /**
     * 分页查询检查项
     *
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<CheckItem> findPage(QueryPageBean queryPageBean) {
        //设定分页信息
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        //设置查询字符串
        if (!StringUtils.isEmpty(queryPageBean.getQueryString())) {
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }

        Page<CheckItem> page = checkItemDao.findByCondition(queryPageBean.getQueryString());
        return new PageResult<CheckItem>(page.getTotal(), page.getResult());
    }

    /**
     * 删除检查项
     *
     * @param id
     */
    @Override
    public void deleteById(int id) throws HealthException{
        int count = checkItemDao.findCountByCheckItemId(id);
        if (count > 0) {
            throw new HealthException("该检查项已经被使用了，不能删除!");
        }
        checkItemDao.deleteById(id);
    }

    /**
     * 回显检查项数据
     * @param id
     * @return
     */
    @Override
    public CheckItem findById(int id) {
        CheckItem checkItem = checkItemDao.findById(id);
        return checkItem;
    }

    /**
     * 编辑更新检查项
     * @param checkItem
     */
    @Override
    public void update(CheckItem checkItem) {
        checkItemDao.update(checkItem);
    }

    /**
     * 查询检查项
     * @return
     */
    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }
}
