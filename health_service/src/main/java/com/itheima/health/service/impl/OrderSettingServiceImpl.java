package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.OrderSettingDao;
import com.itheima.health.exception.HealthException;
import com.itheima.health.pojo.OrderSetting;
import com.itheima.health.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/5/24 19:36
 */
@Service(interfaceClass = OrderSettingService.class)
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingDao orderSettingDao;

    /**
     * 批量导入预约设置
     * @param orderSettingList
     */
    @Override
    @Transactional
    public void add(List<OrderSetting> orderSettingList) {
        for (OrderSetting orderSetting : orderSettingList) {
            // 通过日期查询预约设置信息
            OrderSetting osInDB = orderSettingDao.findByOrderDate(orderSetting.getOrderDate());
            // 存在
            if(null != osInDB) {
                //     判断已预约数量是否大于 将要更新的 可预约数量
                if(osInDB.getReservations() > orderSetting.getNumber()) {
                    //         大于 报错
                    throw new HealthException(orderSetting.getOrderDate() + "中：可预约数不能小于已预约数");
                }else {
                    //         小于要更新可预约数
                    orderSettingDao.updateNumber(orderSetting);
                }
            }else {
                // 不存在 添加预约设置
                orderSettingDao.add(orderSetting);
            }
        }
    }

    /**
     * 通过月份查询预约设置信息
     * @param month
     * @return
     */
    @Override
    public List<Map<String, Integer>> getOrderSettingByMonth(String month) {
        // 拼接开始日期
        String startDate = month + "-01";
        // 结束日期
        String endDate = month + "-31";
        // 调用dao查询
        Map<String,String> map = new HashMap<String,String>();
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        return orderSettingDao.getOrderSettingByMonth(map);
    }
}
