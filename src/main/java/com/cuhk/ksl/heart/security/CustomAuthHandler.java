package com.cuhk.ksl.heart.security;

import com.cuhk.ksl.heart.constant.LoginOutConstant;
import com.cuhk.ksl.heart.util.MessageHelper;
import com.cuhk.ksl.heart.vo.Msg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class CustomAuthHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Msg loginResponse = new Msg();
        loginResponse.setCode(LoginOutConstant.FAILURE_CODE);
        loginResponse.setMessage(LoginOutConstant.LOG_IN_FAILURE_MSG);
        MessageHelper.generalMessage(loginResponse,httpServletResponse);

    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Msg loginResponse = new Msg();
        loginResponse.setCode(LoginOutConstant.SUCCESS_CODE);
        loginResponse.setMessage(LoginOutConstant.LOG_IN_SUCCESS_MSG);
        MessageHelper.generalMessage(loginResponse,httpServletResponse);
        log.info("login success , user:{}",authentication.getName());
    }
}
