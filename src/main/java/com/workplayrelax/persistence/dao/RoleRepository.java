package com.workplayrelax.persistence.dao;

import com.workplayrelax.persistence.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Tomorrow
 * Stephen Wu
 * Copyright 2016
 *
 *
 * https://paulcwarren.wordpress.com/2015/04/03/role-based-spas-with-angularjs-and-spring-hateoas/
 */
@RepositoryRestResource(collectionResourceRel = "role", path = "role")
public interface RoleRepository extends CrudRepository<Role, Long>
{
    Role findByName(String name);

    @Override
    void delete(Role role);
}
