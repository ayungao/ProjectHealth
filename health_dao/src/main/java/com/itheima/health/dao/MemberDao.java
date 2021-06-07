package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.Member;

import java.util.List;

public interface MemberDao {
    /**
     * 查询所有
     *
     * @return
     */
    List<Member> findAll();

    /**
     * 分页条件查询
     *
     * @param queryString
     * @return
     */
    Page<Member> selectByCondition(String queryString);

    /**
     * 添加会员
     *
     * @param member
     */
    void add(Member member);

    /**
     * 通过id删除
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    Member findById(Integer id);

    /**
     * 通过手机号码查询
     *
     * @param telephone
     * @return
     */
    Member findByTelephone(String telephone);

    /**
     * 修改会员
     *
     * @param member
     */
    void edit(Member member);

    /**
     * 统计到某个日期为止，会员总数量
     *
     * @param date
     * @return
     */
    Integer findMemberCountBeforeDate(String date);

    /**
     * 统计某天新增会员数
     *
     * @param date
     * @return
     */
    Integer findMemberCountByDate(String date);

    /**
     * 统计某个日期后的新增会员
     *
     * @param date
     * @return
     */
    Integer findMemberCountAfterDate(String date);

    /**
     * 总会员数
     *
     * @return
     */
    Integer findMemberTotalCount();
}
