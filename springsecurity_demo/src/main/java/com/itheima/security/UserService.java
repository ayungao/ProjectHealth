package com.itheima.security;

import com.itheima.health.pojo.Role;
import com.itheima.health.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 提供登陆用户信息的
 * User: Eric
 */
public class UserService implements UserDetailsService {

    /**
     * 通过用户名获取登陆用户信息（）
     * 方法是给security 登陆时调用
     * @param username 前端传过来的用户名
     * @return  必须包含用户名、密码、权限集合
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库查询到的
        User user = findByUsername(username);
        // security需要的登陆用户信息
        // 用户名、密码、权限集合
        // String username, String password, authorities
        // 这里的密码是明文
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        // 添加角色，授权
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        authorities.add(authority);
        authority = new SimpleGrantedAuthority("ABC");
        authorities.add(authority);
        // security需要登陆用户信息
        org.springframework.security.core.userdetails.User securityUser =
                new org.springframework.security.core.userdetails.User(username,user.getPassword(),authorities);
        return securityUser;
    }

    /**
     * 假设从数据库查询
     * @param username
     * @return
     */
    private User findByUsername (String username){
        if("admin".equals(username)) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword("$2a$10$u/BcsUUqZNWUxdmDhbnoeeobJy6IBsL1Gn/S0dMxI2RbSgnMKJ.4a");
            return user;
        }
        return null;
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // encode加密
        //System.out.println(encoder.encode("1234"));

        // 密码匹配
        // 明文
        // 密文
        System.out.println(encoder.matches("1234", "$2a$10$u/BcsUUqZNWUxdmDhbnoeeobJy6IBsL1Gn/S0dMxI2RbSgnMKJ.4a"));
        System.out.println(encoder.matches("1234", "$2a$10$7y4NCbRaAMvdr0RYgs8f5eyiwy28vvOO0.Hcy2neUDt5UpWfpSgTm"));
        System.out.println(encoder.matches("1234", "$2a$10$qzHiyQc.Xn78nkurepFJAeED2A3BIFgnQh/RP89ZeTZMIueb2i4jK"));
        System.out.println(encoder.matches("1234", "$2a$10$XOripk.Z/c2X5.AJdWxrEOSX9vNc9A8jmrDyCcdXcLZS7BGuATq0G"));
    }
}
