package com.workplayrelax.config;

import com.workplayrelax.filter.CsrfHeaderFilter;
import com.workplayrelax.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

/**
 * Tomorrow
 * Stephen Wu
 * Copyright 2016
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Value("${tomorrow.authentication.secret}")
    String secret;

    public SecurityConfiguration()
    {
    }

    @Override
    protected void configure(HttpSecurity http)
        throws Exception
    {
        http
            .httpBasic()
            .and().authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers(
                "/index.html",
                "/home.html",
                "/faq.html",
                "/about.html",
                "/login.html").permitAll()
            .antMatchers("**/.css").permitAll()
            .antMatchers("**/.js").permitAll()
            .antMatchers("/user/**").permitAll()
            .anyRequest().authenticated()
            .and().csrf().csrfTokenRepository(csrfTokenRepository())
            .and().addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
    }

    @Bean
    public FilterRegistrationBean jwtFilter()
    {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter(secret));
        registrationBean.addUrlPatterns("/api/*");

        return registrationBean;
    }

    private CsrfTokenRepository csrfTokenRepository()
    {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
        throws Exception
    {
        auth
            .inMemoryAuthentication()
            .withUser("user")
            .password("password")
            .roles("USER");
    }

}
