package com.itheima.health.security;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.pojo.Permission;
import com.itheima.health.pojo.Role;
import com.itheima.health.pojo.User;
import com.itheima.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: AyuNGao
 * @date: 21/7/20 23:33
 */
@Component
public class SpringSecurityUserService implements UserDetailsService {

    @Reference
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        通过用户名查询用户信息
        User user = userService.UserfindByUsername(username);
//          用户权限集合
        List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();

//        能查到，就授权
        if (null != user) {
            GrantedAuthority authority = null;
            Set<Role> roles = user.getRoles();
            if (null != roles) {
                for (Role role : roles) {
                    Set<Permission> permissions = role.getPermissions();
                    if (null != permissions) {
                        for (Permission permission : permissions) {
                            authority = new SimpleGrantedAuthority(permission.getKeyword());
                            authorityList.add(authority);
                        }
                    }
                }
            }
            return new org.springframework.security.core.userdetails.User(username,user.getPassword(),authorityList);
        }
//        查不到用户就返回null
        return null;
    }
}
