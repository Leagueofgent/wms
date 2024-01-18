package com.warehouse.management.wms.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;


/**
 * 认证失败之后的处理逻辑
 */
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    private String url;

    public AuthenticationFailureHandlerImpl(String url) {
        this.url = url;
    }

    /**
     * 认证失败之后的处理代码
     *
     * @param request   request
     * @param response  response
     * @param exception exception
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.sendRedirect(url);
    }
}
