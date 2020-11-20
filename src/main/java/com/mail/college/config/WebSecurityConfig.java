package com.mail.college.config;

import com.mail.college.handler.MyFailureHandler;
import com.mail.college.handler.MySuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @author lemon
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private MySuccessHandler successHandler;

    @Resource
    private MyFailureHandler failHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/myinfo").hasRole("USER")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                //.loginPage("/login")  // 登录页面
                .usernameParameter("username")
                .passwordParameter("password")
                //.loginProcessingUrl("/signin")  // 登录处理请求，也就是 submit 按钮提交的请求
                //.successHandler(successHandler)  // 登录成功处理器
                //.failureHandler(failHandler)  // 登录失败处理器
                //.successForwardUrl("/index")  // 登录成功重定向
                //.failureForwardUrl("/login")  // 登录失败重定向
                .and()
                .csrf().disable();  // 禁止跨域请求
    }

    // todo 后面再学习一下

}
