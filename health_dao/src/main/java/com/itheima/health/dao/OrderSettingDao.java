package com.itheima.health.dao;

import com.itheima.health.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/5/24 19:37
 */
public interface OrderSettingDao {
    /**
     * 通过日期查询预约设置信息
     * @param orderDate
     * @return
     */
    OrderSetting findByOrderDate(Date orderDate);

    /**
     * 更新可预约数
     * @param orderSetting
     */
    void updateNumber(OrderSetting orderSetting);

    /**
     * 添加预约设置
     * @param orderSetting
     */
    void add(OrderSetting orderSetting);

    /**
     * 通过月份查询预约设置信息
     * @param map
     * @return
     */
    List<Map<String, Integer>> getOrderSettingByMonth(Map<String, String> map);
}
