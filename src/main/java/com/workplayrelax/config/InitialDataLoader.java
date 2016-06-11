package com.workplayrelax.config;

import com.workplayrelax.persistence.dao.PrivilegeRepository;
import com.workplayrelax.persistence.dao.RoleRepository;
import com.workplayrelax.persistence.model.Privilege;
import com.workplayrelax.persistence.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Tomorrow
 * Stephen Wu
 * Copyright 2016
 */
@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent>
{
    Logger LOGGER = LoggerFactory.getLogger(getClass());
    boolean alreadySetup = false;

    @Autowired
    PrivilegeRepository privilegeRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        if (alreadySetup)
        {
            return;
        }
        else
        {
            Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
            Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
            Privilege changeUserPrivilege = createPrivilegeIfNotFound("CHANGE_USER_PRIVILEGE");
            List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege, changeUserPrivilege);

            createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
            createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege, writePrivilege));
            createRoleIfNotFound("ROLE_GUEST", Arrays.asList(readPrivilege));

            alreadySetup = true;
        }
    }

    @Transactional
    private Privilege createPrivilegeIfNotFound(String name)
    {
        Privilege privilege = privilegeRepository.findByName(name);
        if(privilege == null)
        {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    private Role createRoleIfNotFound(String name, Collection<Privilege> privileges)
    {
        Role role = roleRepository.findByName(name);
        if(role == null)
        {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}
