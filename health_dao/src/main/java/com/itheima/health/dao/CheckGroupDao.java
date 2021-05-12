package com.itheima.health.dao;

import com.itheima.health.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

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
}
