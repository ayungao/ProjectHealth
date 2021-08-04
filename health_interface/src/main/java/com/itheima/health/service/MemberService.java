package com.itheima.health.service;

import com.itheima.health.pojo.Member;

import java.util.List;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/6/8 1:58
 */
public interface MemberService {
    /**
     * 会员判断
     * @param telephone
     * @return
     */
    Member findByTelephone(String telephone);

    /**
     * 添加新会员
     * @param member
     */
    void add(Member member);

    /**
     * 统计每个月最后一天时 会员的总数量
     * @param months
     * @return
     */
    List<Integer> getMemberReport(List<String> months);
}
