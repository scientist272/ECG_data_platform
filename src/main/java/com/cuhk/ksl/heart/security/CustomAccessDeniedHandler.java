package com.cuhk.ksl.heart.security;

import com.cuhk.ksl.heart.constant.LoginOutConstant;
import com.cuhk.ksl.heart.util.MessageHelper;
import com.cuhk.ksl.heart.vo.Msg;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AuthenticationEntryPoint, AccessDeniedHandler {
    //未登录时的处理
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Msg msg = new Msg();
        msg.setCode(LoginOutConstant.FAILURE_CODE);
        msg.setMessage(LoginOutConstant.NO_LOG_MSG);
        MessageHelper.generalMessage(msg,httpServletResponse);
    }
    //无权限访问资源的处理
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        Msg msg = new Msg();
        msg.setCode(LoginOutConstant.FAILURE_CODE);
        msg.setMessage(LoginOutConstant.ACCESS_DENIED_MSG);
        MessageHelper.generalMessage(msg,httpServletResponse);
    }
}
