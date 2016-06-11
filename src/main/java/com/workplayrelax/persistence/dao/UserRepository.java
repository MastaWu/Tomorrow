package com.workplayrelax.persistence.dao;

import com.workplayrelax.persistence.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Tomorrow
 * Stephen Wu
 * Copyright 2016
 */
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(@Param("username") String username);

    User findByEmail(@Param("email") String email);

    @Override
    void delete(User user);
}
