package com.workplayrelax.dto;

/**
 * Tomorrow
 * Stephen Wu
 * Copyright 2016
 */
public class UserDTO
{
    private String username;
    private String email;
    private String password;

    public void setUsername(final String username)
    {
        this.username = username;
    }

    public void setEmail(final String email)
    {
        this.email = email;
    }

    public void setPassword(final String password)
    {
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }
}
