package com.cuhk.ksl.heart.security;

import com.alibaba.fastjson.JSON;
import com.cuhk.ksl.heart.vo.LoginRequest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class CustomAuthFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationToken;
        //不允许用GET方法登录
        if(request.getMethod().equals("GET")){
            authenticationToken = new UsernamePasswordAuthenticationToken("", "");
            return this.getAuthenticationManager().authenticate(authenticationToken);
        }

            //登录验证逻辑
        if(request.getContentType()!=null && request.getContentType().equals(MediaType.APPLICATION_JSON.toString())){

            try (InputStream inputStream = request.getInputStream()) {
                LoginRequest loginRequest = JSON.parseObject(inputStream, LoginRequest.class);
                String userName = loginRequest.getUserName();
                String password = loginRequest.getPassword();
                authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
            } catch (IOException e) {
                e.printStackTrace();
                authenticationToken = new UsernamePasswordAuthenticationToken("", "");
            }
            setDetails(request, authenticationToken);
        }else{
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            authenticationToken = new UsernamePasswordAuthenticationToken(userName,password);
        }
                return this.getAuthenticationManager().authenticate(authenticationToken);

        }


    }

