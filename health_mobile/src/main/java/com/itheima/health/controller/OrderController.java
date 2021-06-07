package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.constant.RedisMessageConstant;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Order;
import com.itheima.health.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/6/6 23:48
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private JedisPool jedisPool;

    @Reference
    private OrderService orderService;

    /**
     * 提交预约
     * @param paraMap
     * @return
     */
    @Transactional
    @RequestMapping("/submit")
    public Result submit(@RequestBody Map<String, String> paraMap) {
//        看下验证码对不对
        Jedis jedis = jedisPool.getResource();
        String key = RedisMessageConstant.SENDTYPE_ORDER + ":" + paraMap.get("telephone");
        String value = jedis.get(key);
        if (StringUtils.isEmpty(value)) {
            return new Result(false, "请重新获取验证码");
        }
        // 前端传的验证码
        String validateCode = paraMap.get("validateCode");
        if (!value.equals(validateCode)) {
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
//        删除验证码，避免被重复使用
//        jedis.del(key);
        // 设置预约的类型
        paraMap.put("orderType", Order.ORDERTYPE_WEIXIN);

        Order order = orderService.submitOrder(paraMap);
        return new Result(true, MessageConstant.ORDER_SUCCESS, order);
    }

    /**
     * 成功信息的展示
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public Result findById(int id){
        Map<String,Object> orderDetail = orderService.findOrderDetailById(id);
        return new Result(true, MessageConstant.ORDER_SUCCESS,orderDetail);
    }
}
