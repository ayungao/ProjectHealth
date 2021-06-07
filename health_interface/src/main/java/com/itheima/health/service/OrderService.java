package com.itheima.health.service;

import com.itheima.health.exception.HealthException;
import com.itheima.health.pojo.Order;

import java.util.Map;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/6/6 23:52
 */
public interface OrderService {
    /**
     * 提交预约
     *
     * @param paraMap
     * @return
     */
    Order submitOrder(Map<String, String> paraMap) throws HealthException;

    /**
     * 成功信息的展示
     * @param id
     * @return
     */
    Map<String, Object> findOrderDetailById(int id);
}
