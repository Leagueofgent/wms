package com.warehouse.management.wms.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

/**
 * 认证成功后，代码处理逻辑
 */
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    /* 认证成功后的登陆地址 */
    private String url;
    /* 是否重定向 */
    private boolean isRedirect;

    public AuthenticationSuccessHandlerImpl(String url, boolean isRedirect) {
        this.url = url;
        this.isRedirect = isRedirect;
    }

    /**
     * 认证成功后，具体执行的代码
     *
     * @param request        request
     * @param response       response
     * @param authentication 认证成功后的用户主体对象 包含个人信息和权限列表
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        if (isRedirect) {
            //重定向
            response.sendRedirect(url);
        } else {
            //请求转发
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
