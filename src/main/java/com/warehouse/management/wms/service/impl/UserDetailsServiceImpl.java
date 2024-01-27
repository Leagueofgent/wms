package com.warehouse.management.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.warehouse.management.wms.entity.SysUser;
import com.warehouse.management.wms.mapper.SysUserMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 根据用户名查询用户对象
     *
     * @param username the username identifying the user whose data is required.
     * @return 用户名 密码 权限
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        log.info("UserDetailsServiceImpl的username:{}", username);
        queryWrapper.eq(SysUser::getUserName, username);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        //查询不到用户
        if (Objects.isNull(sysUser)) {
            throw new RuntimeException("用户名或者密码错误");
        }

        //开始查询已登陆用户的权限
        //查询角色


        return new User(username, sysUser.getPassword(), null);
    }
}
