package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/5/12 20:55
 */
public interface CheckGroupDao {
    /**
     * 添加检查组
     *
     * @param checkGroup
     */
    void add(CheckGroup checkGroup);

    /**
     * 关联检查组id和检查项id
     *
     * @param checkGroupId
     * @param checkitemId
     */
    void addCheckGroupCheckItem(@Param("checkGroupId") Integer checkGroupId, @Param("checkitemId") Integer checkitemId);

    /**
     * 分页查询检查组
     * @param queryString
     * @return
     */
    Page<CheckGroup> findPage(String queryString);

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
     * 更新检查组
     * @param checkGroup
     */
    void update(CheckGroup checkGroup);

    /**
     * 删除检查组和检查项的关联
     * @param id
     */
    void deleteCheckGroupCheckItem(Integer id);
}
