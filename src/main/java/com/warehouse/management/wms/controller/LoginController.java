package com.warehouse.management.wms.controller;

import com.warehouse.management.wms.comment.Result;
import com.warehouse.management.wms.entity.SysUser;
import com.warehouse.management.wms.service.LoginService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class LoginController {

    @Resource
    private LoginService loginService;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    @RequestMapping("/failure")
    public String failure(){
        return "failure";
    }

/**
    @RequestMapping("/login")
    public Result login(@RequestBody SysUser sysUser) {
        log.info("登陆方法的username:{}", sysUser.getUserName());
        log.info("登陆方法的password:{}", sysUser.getPassword());
        return loginService.login(sysUser);
    }
*/
    @GetMapping("/logout")
    public Result logout() {
        log.info("访问进入");
        return null;
    }


}
