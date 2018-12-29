package com.sinoyd.survey.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * SpringSecurity的配置
 * 这里的代码是针对com.sinoyd.core包而言
 */

// --------  the way of one  --------
@Configuration
@EnableWebSecurity//(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)


// --------  the way of two --------
//在yml未见配置security

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // 设置 HTTP 验证规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/gis/**", "/html/**", "/static/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/**").permitAll()//放行get接口
//                .anyRequest().authenticated()  // 剩下的所有请求需要身份认证
                .and().headers().frameOptions().disable()// 禁用x-frame 、
                .and();
    }
}