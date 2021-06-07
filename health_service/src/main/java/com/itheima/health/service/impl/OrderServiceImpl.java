package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.MemberDao;
import com.itheima.health.dao.OrderDao;
import com.itheima.health.dao.OrderSettingDao;
import com.itheima.health.exception.HealthException;
import com.itheima.health.pojo.Member;
import com.itheima.health.pojo.Order;
import com.itheima.health.pojo.OrderSetting;
import com.itheima.health.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/6/6 23:52
 */
@Service(interfaceClass = OrderService.class)
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderSettingDao orderSettingDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private OrderDao orderDao;

    /**
     * 提交预约
     *
     * @param paraMap
     * @return
     */
    @Override
    @Transactional
    public Order submitOrder(Map<String, String> paraMap) throws HealthException {
//        看看能不能接收到正确的预约日期
        String orderDateStr = paraMap.get("orderDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date orderDate = null;
        try {
            orderDate = sdf.parse(orderDateStr);
        } catch (ParseException e) {
            throw new HealthException("日期格式错误");
        }

//      看看那天开不开门
        OrderSetting orderSetting = orderSettingDao.findByOrderDate(orderDate);
        if (null == orderSetting) {
            throw new HealthException("所选日期，不能预约");
        }

//        看看有没有约满
        if (orderSetting.getReservations() >= orderSetting.getNumber()) {
            //   full 报错
            throw new HealthException("所选日期，预约已满");
        }

//        通过手机号查查这人是不是会员
        String telephone = paraMap.get("telephone");
        Member member = memberDao.findByTelephone(telephone);
        if (null == member) {
            member = new Member();
            member.setIdCard(paraMap.get("idCard"));
            member.setName(paraMap.get("name"));
            member.setPhoneNumber(telephone);
            member.setRegTime(new Date());
            member.setSex(paraMap.get("sex"));

//            获取了返回的memberId
            memberDao.add(member);
        }

//        通过member、预约日期和套餐判断重复预约
        Order order = new Order();
        order.setOrderDate(orderDate);
        order.setMemberId(member.getId());
        order.setSetmealId(Integer.valueOf(paraMap.get("setmealId")));
        List<Order> orderList = orderDao.findByCondition(order);

        if (null != orderList && orderList.size() > 0) {
            throw new HealthException("不能重复预约");
        }

//        可预约，添加订单
        order.setOrderType(paraMap.get("orderType"));
        order.setOrderStatus(Order.ORDERSTATUS_NO);
        orderDao.add(order);
        //4. 更新已预约数量 t_ordersetting
        orderSettingDao.editReservationsByOrderDate(orderSetting);
        return order;
    }

    /**
     * 成功信息的展示
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> findOrderDetailById(int id) {
        return orderDao.findById4Detail(id);
    }
}
