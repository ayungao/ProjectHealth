package com.itheima.health.service;

import com.itheima.health.pojo.OrderSetting;
import com.sun.xml.internal.ws.handler.HandlerException;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/5/24 19:35
 */
public interface OrderSettingService {
    /**
     * 批量导入预约设置
     * @param orderSettingList
     */
    void add(List<OrderSetting> orderSettingList);

    /**
     * 通过月份查询预约设置信息
     * @param month
     * @return
     */
    List<Map<String, Integer>> getOrderSettingByMonth(String month);

    /**
     * 基于日历的预约设置
     * @param orderSetting
     */
    void editNumberByDate(OrderSetting orderSetting) throws HandlerException;
}
