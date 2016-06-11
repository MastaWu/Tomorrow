package com.workplayrelax.service;

import com.workplayrelax.dto.UserDTO;
import com.workplayrelax.persistence.dao.RoleRepository;
import com.workplayrelax.persistence.dao.UserRepository;
import com.workplayrelax.persistence.model.Role;
import com.workplayrelax.persistence.model.User;
import com.workplayrelax.validation.EmailExistsException;
import com.workplayrelax.validation.UsernameExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;

/**
 * Tomorrow
 * Stephen Wu
 * Copyright 2016
 */
@Service
@Transactional
public class UserService
{
    public UserService()
    {

    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public final User loadUserByUsername(String username)
        throws UsernameNotFoundException
    {
        final User user = userRepository.findByUsername(username);
        if(user == null)
        {
            throw new UsernameNotFoundException("User not found.");
        }
        return user;
    }

    public final Collection<Role> findUserRole(String username)
        throws UsernameNotFoundException
    {
        User user = userRepository.findByUsername(username);
        final Collection<Role> role = user.getRoles();

        return role;
    }

    // TODO: Remove exception throw
    public User registerNewUserAccount(final UserDTO userCredentials)
        throws EmailExistsException, UsernameExistsException
    {
        if (emailExists(userCredentials.getEmail()) )
        {
            throw new EmailExistsException(userCredentials.getEmail() + "(email) already exists.");
        }
        else if (usernameExists(userCredentials.getUsername()))
        {
            throw new UsernameExistsException(userCredentials.getUsername() + "(username) already exists");
        }

        final User user = new User();

        user.setUsername(userCredentials.getUsername());
        user.setEmail(userCredentials.getEmail());
        user.setPassword(userCredentials.getPassword());

        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return userRepository.save(user);
    }

    public boolean emailExists(final String email)
    {
        final User user = userRepository.findByEmail(email);

        if(user != null)
        {
            return true;
        }

        return false;
    }

    public boolean usernameExists(final String username)
    {
        final User user = userRepository.findByUsername(username);

        if(user != null)
        {
            return true;
        }

        return false;
    }

}
