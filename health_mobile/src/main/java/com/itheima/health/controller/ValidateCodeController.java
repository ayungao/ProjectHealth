package com.itheima.health.controller;

import com.itheima.health.constant.MessageConstant;
import com.itheima.health.constant.RedisMessageConstant;
import com.itheima.health.entity.Result;
import com.itheima.health.utils.TencentSMSUtils;
import com.itheima.health.utils.ValidateCodeUtils;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/6/6 22:40
 */
@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {
    @Autowired
    private JedisPool jedisPool;

    /**
     * 预约界面发送验证码
     *
     * @param telephone
     * @return
     */
    @RequestMapping("/send4Order")
    public Result send4Order(String telephone) {
        Jedis jedis = jedisPool.getResource();
        String key = RedisMessageConstant.SENDTYPE_ORDER + ":" + telephone;
        String value = jedis.get(key);
        if (value != null) {
            return new Result(false, "验证码已经发送过了，请注意查收");
        }
//        生成验证码
        String validateCode = ValidateCodeUtils.generateValidateCode(6) + "";
        try {
//        发送验证码到手机
            TencentSMSUtils.sendShortMessage(TencentSMSUtils.VALIDATE_CODE, telephone, validateCode);
//        写入验证码到redis，持续10分钟
            jedis.setex(key, 10 * 60, validateCode);
            jedis.close();
            return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
    }

    /**
     * 登录发送验证码
     *
     * @return
     */
    @RequestMapping("/send4Login")
    public Result send4Login(String telephone) {
        Jedis jedis = jedisPool.getResource();
        String key = RedisMessageConstant.SENDTYPE_LOGIN + ":" + telephone;
        String value = jedis.get(key);
        if (value != null) {
            return new Result(false, "验证码已经发送过了，请注意查收");
        }
//        生成验证码
        String validateCode = ValidateCodeUtils.generateValidateCode(6) + "";
        try {
//        发送验证码到手机
            TencentSMSUtils.sendShortMessage(TencentSMSUtils.VALIDATE_CODE, telephone, validateCode);
//        写入验证码到redis，持续10分钟
            jedis.setex(key, 10 * 60, validateCode);
            jedis.close();
            return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
    }
}
