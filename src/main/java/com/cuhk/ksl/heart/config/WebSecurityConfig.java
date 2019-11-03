package com.cuhk.ksl.heart.config;

import com.cuhk.ksl.heart.security.CustomAccessDeniedHandler;
import com.cuhk.ksl.heart.security.CustomAuthFilter;
import com.cuhk.ksl.heart.security.CustomAuthHandler;
import com.cuhk.ksl.heart.security.CustomLogOutHandler;
import com.cuhk.ksl.heart.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomAuthHandler customAuthHandler;
    private final UserDetailServiceImpl userDetailService;
    private final CustomLogOutHandler customLogOutHandler;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    @Autowired
    public WebSecurityConfig(UserDetailServiceImpl userDetailService, CustomAuthHandler customAuthHandler, CustomLogOutHandler customLogOutHandler, CustomAccessDeniedHandler accessDeniedHandler) {
        this.userDetailService = userDetailService;
        this.customAuthHandler = customAuthHandler;
        this.customLogOutHandler = customLogOutHandler;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
                .antMatchers("/heart/**").hasAnyAuthority("BASIC")
                .antMatchers("/test/entity").hasAuthority("BASIC");



        http.formLogin().
                and().
                sessionManagement().
                maximumSessions(3).
                maxSessionsPreventsLogin(true);//不允许异地session存在

        http.exceptionHandling().
                accessDeniedHandler(accessDeniedHandler).
                authenticationEntryPoint(accessDeniedHandler);
        http.logout().logoutUrl("/logout").logoutSuccessHandler(customLogOutHandler);
        http.csrf().disable();
        http.addFilterAt(customAuthFilter(), UsernamePasswordAuthenticationFilter.class);

    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/static/**").antMatchers("/heart/login");
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CustomAuthFilter customAuthFilter() throws Exception {
        CustomAuthFilter filter = new CustomAuthFilter();
        filter.setFilterProcessesUrl("/login");
        filter.setAuthenticationSuccessHandler(customAuthHandler);
        filter.setAuthenticationFailureHandler(customAuthHandler);
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

}