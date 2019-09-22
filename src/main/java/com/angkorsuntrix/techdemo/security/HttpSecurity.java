package com.angkorsuntrix.techdemo.security;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configurable
@EnableWebSecurity
public class HttpSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(org.springframework.security.config.annotation.web.builders.HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf()
                .disable();
    }
}
