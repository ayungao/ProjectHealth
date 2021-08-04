package com.itheima.health.dao;

import com.itheima.health.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    void add(Order order);

    List<Order> findByCondition(Order order);

    Map findById4Detail(Integer id);

    Integer findOrderCountByDate(String date);

    Integer findOrderCountAfterDate(String date);

    Integer findVisitsCountByDate(String date);

    Integer findVisitsCountAfterDate(String date);

    List<Map<String, Object>> findHotSetmeal();

    int findOrderCountBetweenDate(@Param("startDate") String monday, @Param("endDate") String sunday);
}
