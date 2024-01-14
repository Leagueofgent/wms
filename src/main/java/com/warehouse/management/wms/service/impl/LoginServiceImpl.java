package com.warehouse.management.wms.service.impl;

import com.warehouse.management.wms.comment.Result;
import com.warehouse.management.wms.config.RedisCache;
import com.warehouse.management.wms.entity.LoginUser;
import com.warehouse.management.wms.entity.SysUser;
import com.warehouse.management.wms.service.LoginService;
import com.warehouse.management.wms.util.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisCache cache;

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
        LoginUser principal = (LoginUser) authenticate.getPrincipal();
        String id = principal.getSysUser().getId().toString();
        String jwt = JwtUtil.createJwt(id);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        cache.setCacheObject("login:" + id, principal);
        return Result.ok().data("map", map).message("登陆成功");
    }
}
