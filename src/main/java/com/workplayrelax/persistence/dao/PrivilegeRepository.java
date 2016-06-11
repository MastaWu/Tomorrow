package com.workplayrelax.persistence.dao;

import com.workplayrelax.persistence.model.Privilege;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Tomorrow
 * Stephen Wu
 * Copyright 2016
 */
@RepositoryRestResource(collectionResourceRel = "privilege", path = "privilege")
public interface PrivilegeRepository extends CrudRepository<Privilege, Long>
{
    Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);
}
