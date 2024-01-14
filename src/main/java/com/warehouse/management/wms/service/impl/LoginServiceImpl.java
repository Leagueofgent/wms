package com.warehouse.management.wms.service.impl;

import com.warehouse.management.wms.comment.Result;
import com.warehouse.management.wms.entity.SysUser;
import com.warehouse.management.wms.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public Result login(SysUser sysUser) {
        //进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(sysUser.getUserName(), sysUser.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //认证不通过进行提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登陆失败");
        }
        //认证通过生成jwt并存入Result返回

        return null;
    }
}
