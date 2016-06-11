package com.workplayrelax.controller;

import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Tomorrow
 * Stephen Wu
 * Copyright 2016
 */
@RestController
@RequestMapping("/api")
public class ApiController
{
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "role", method = RequestMethod.GET)
    @ResponseBody
    public Claims login(final HttpServletRequest request)
        throws ServletException
    {
        final Claims claims = (Claims) request.getAttribute("claims");

        System.out.println(claims);
        return claims;
    }
}
