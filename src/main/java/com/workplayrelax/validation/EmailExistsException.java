package com.workplayrelax.validation;

/**
 * Tomorrow
 * Stephen Wu
 * Copyright 2016
 */
public class EmailExistsException extends Throwable
{
    public EmailExistsException(final String message)
    {
        super(message);
    }
}
