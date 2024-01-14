package com.warehouse.management.wms.controller;

import com.warehouse.management.wms.comment.Result;
import com.warehouse.management.wms.entity.SysUser;
import com.warehouse.management.wms.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody SysUser sysUser) {
        System.out.println(sysUser.getUserName());
        System.out.println(sysUser.getPassword());
        return  loginService.login(sysUser);
    }

}
