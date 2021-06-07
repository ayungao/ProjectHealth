package com.itheima.health.service;

import com.itheima.health.pojo.Member;

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
}
