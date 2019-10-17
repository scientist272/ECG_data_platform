package com.cuhk.ksl.heart.security;

import com.cuhk.ksl.heart.constant.LoginOutConstant;
import com.cuhk.ksl.heart.util.MessageHelper;
import com.cuhk.ksl.heart.vo.Msg;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomLogOutHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Msg msg = new Msg();
        msg.setCode(LoginOutConstant.SUCCESS_CODE);
        msg.setMessage(LoginOutConstant.LOG_OUT_SUCCESS_MSG);
        MessageHelper.generalMessage(msg,httpServletResponse);
    }
}
