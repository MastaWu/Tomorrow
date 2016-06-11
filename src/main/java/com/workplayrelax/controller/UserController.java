package com.workplayrelax.controller;

import com.workplayrelax.dto.UserDTO;
import com.workplayrelax.persistence.dao.UserRepository;
import com.workplayrelax.persistence.model.User;
import com.workplayrelax.response.LoginResponse;
import com.workplayrelax.response.RegistrationResponse;
import com.workplayrelax.service.UserService;
import com.workplayrelax.validation.EmailExistsException;
import com.workplayrelax.validation.UsernameExistsException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Tomorrow
 * Stephen Wu
 * Copyright 2016
 */
@RestController
@RequestMapping("/user")
public class UserController
{
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    public UserController()
    {

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody LoginResponse login(@RequestBody final UserDTO login)
        throws ServletException
    {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + TimeUnit.HOURS.toMillis(12));

        System.out.println("\n\n\n\n\nUsername: " + login.getUsername() + "\n\n\n\n\n");

        LOGGER.warn(login.getUsername());
        User user = userRepository.findByUsername(login.getUsername());

        if(login.getUsername() == null)
        {
            throw new ServletException("Invalid login");
        }

        // After getting the database preloaded add back this:
        // .claim("role", userService.findUserRole(user.getUsername()))
        String token = Jwts.builder()
            .setId(UUID.randomUUID().toString())
            .setSubject(user.getUsername())
            .claim("role", "ROLE_ADMIN")
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(SignatureAlgorithm.HS512, "secret")
            .compact();

        return new LoginResponse(token);
    }

    @RequestMapping(value = "/checkUsernameExists/{username}", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkUsernameExists(@PathVariable("username") String username)
    {
        LOGGER.warn(username);
        return userService.usernameExists(username);
    }

    @RequestMapping(value = "/checkEmailExists/{email}", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkEmailExists(@PathVariable("email") String email)
    {
        LOGGER.info(email);
        return userService.emailExists(email);
    }

    // TODO: Remove exception throw
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public RegistrationResponse registerNewAccount(@Valid final UserDTO login, final HttpServletRequest request)
        throws EmailExistsException, UsernameExistsException
    {
        LOGGER.debug("Registering user account with information: {}", login);

        final User registered = userService.registerNewUserAccount(login);

        return new RegistrationResponse("Registration Success!");
    }
}
