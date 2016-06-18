package com.workplayrelax.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Tomorrow
 * Stephen Wu
 * Copyright 2016
 */
@Configuration
public class JwtFilter extends GenericFilterBean
{
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private Environment env;

    private String secret;

    public JwtFilter(@Value("${tomorrow.authentication.secret}") String secret)
    {
        this.secret = secret;
    }

    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain)
        throws IOException, ServletException
    {
        final HttpServletRequest request = (HttpServletRequest) req;

        System.out.println(request);

        final String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer "))
        {
            throw new ServletException("Missing or invalid Authorization header.");
        }

        final String token = authHeader.substring(7);

        try
        {
            final Claims claims =
                Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            request.setAttribute("claims", claims);
        }
        catch(final SignatureException e)
        {
            throw new ServletException("Invalid token.", e);
        }

        chain.doFilter(req, res);
    }
}
