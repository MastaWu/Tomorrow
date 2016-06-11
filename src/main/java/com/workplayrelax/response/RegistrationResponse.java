package com.workplayrelax.response;

/**
 * Tomorrow
 * Stephen Wu
 * Copyright 2016
 */
public class RegistrationResponse
{
    private String message;
    private String error;

    public RegistrationResponse(final String message)
    {
        this.message = message;
    }

    public RegistrationResponse(final String message, final String error)
    {
        this.message = message;
        this.error = error;
    }

    public String getMessage()
    {
        return message;
    }

    public String getError()
    {
        return error;
    }

    public void setMessage(final String message)
    {
        this.message = message;
    }

    public void setError(final String error)
    {
        this.error = error;
    }
}
