package com.workplayrelax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Tomorrow
 * Stephen Wu
 * Copyright 2016
 */
@Controller
public class RoutesController
{
    @RequestMapping({
        "/about",
        "/faq",
        "/login"
    })
    public String index() {
        return "forward:/index.html";
    }
}
