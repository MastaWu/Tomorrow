package com.workplayrelax.persistence.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Tomorrow
 * Stephen Wu
 * Copyright 2016
 */
@Entity
public class Privilege
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

    public Privilege()
    {

    }

    public Privilege(final String name)
    {
        this.name = name;
    }

    public void setId(final long id)
    {
        this.id = id;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public void setRoles(final Collection<Role> roles)
    {
        this.roles = roles;
    }

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public Collection<Role> getRoles()
    {
        return roles;
    }
}
