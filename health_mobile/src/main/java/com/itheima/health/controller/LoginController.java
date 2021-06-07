package com.itheima.health.controller;

import com.itheima.health.constant.MessageConstant;
import com.itheima.health.constant.RedisMessageConstant;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Member;
import com.itheima.health.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/6/8 1:56
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/check")
    public Result check(@RequestBody Map<String, String> paramMap, HttpServletResponse res) {
        Jedis jedis = jedisPool.getResource();
        String key = RedisMessageConstant.SENDTYPE_LOGIN + ":" + paramMap.get("telephone");
        String value = jedis.get(key);

        if (StringUtils.isEmpty(value)) {
//            没值 重新发送
            return new Result(false, "请重新获取验证码!");
        }

        if (!value.equals(paramMap.get("validateCode"))) {
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }

        // 移除redis中的验证码，防止重复提交，
//        jedis.del(key);
        jedis.close();

        // 会员判断
        Member member = memberService.findByTelephone(paramMap.get("telephone"));

        if (null == member) {
            // 添加新会员
            member = new Member();
            member.setPhoneNumber(paramMap.get("telephone"));
            member.setRegTime(new Date());
            memberService.add(member);
        }

        Cookie cookie = new Cookie("login_member_telephone", paramMap.get("telephone"));
        cookie.setMaxAge(30 * 24 * 60 * 60);//30天
        cookie.setPath("/");
        res.addCookie(cookie);
        return new Result(true, MessageConstant.LOGIN_SUCCESS);
    }

}
