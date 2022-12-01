package com.example.Tischmesse.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception { http
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/exhibitor/add").permitAll()
            .antMatchers("/confirmation").permitAll()
            .antMatchers("/css/**").permitAll()
            .antMatchers("/img/**").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin(Customizer.withDefaults()); }
}