package com.workplayrelax.persistence.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Tomorrow
 * Stephen Wu
 * Copyright 2016
 */
@Entity
@Table(name = "user")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String email;

    private boolean enabled;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    public User()
    {
        this.enabled = false;
    }

    public User(String username, String password, String email)
    {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setRoles(final Collection<Role> roles)
    {
        this.roles = roles;
    }

    public void setEnabled(final boolean enabled)
    {
        this.enabled = enabled;
    }

    public long getId()
    {
        return id;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getEmail()
    {
        return email;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public Collection<Role> getRoles()
    {
        return roles;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if(this == obj)
        {
            return true;
        }
        if(obj == null)
        {
            return false;
        }
        if(getClass() != obj.getClass())
        {
            return false;
        }

        final User user = (User) obj;
        if(!email.equals(user.email))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return String.format(
            "Username[id=%d, username='%s', email='%s']",
            id, username, email
        );
    }
}
