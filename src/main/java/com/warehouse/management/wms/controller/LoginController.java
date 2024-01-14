package com.warehouse.management.wms.controller;

import com.warehouse.management.wms.comment.Result;
import com.warehouse.management.wms.entity.SysUser;
import com.warehouse.management.wms.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {

    @Resource
    private LoginService loginService;

    @RequestMapping("/login")
    public String toLogin() {
        return "login";
    }

}
