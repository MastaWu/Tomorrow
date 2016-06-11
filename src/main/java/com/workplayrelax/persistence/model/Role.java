package com.workplayrelax.persistence.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Tomorrow
 * Stephen Wu
 * Copyright 2016
 */
@Entity
public class Role
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany
    @JoinTable(name = "roles_privileges", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    private String name;

    public Role()
    {

    }

    public Role(final String name)
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

    public void setUsers(final Collection<User> users)
    {
        this.users = users;
    }

    public void setPrivileges(final Collection<Privilege> privileges)
    {
        this.privileges = privileges;
    }

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public Collection<User> getUsers()
    {
        return users;
    }

    public Collection<Privilege> getPrivileges()
    {
        return privileges;
    }
}
