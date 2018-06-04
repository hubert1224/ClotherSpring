package com.test.spring.hubert.sprinttest;

import com.test.spring.hubert.sprinttest.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUserPrincipal implements UserDetails
{
    private User user;

    public MyUserPrincipal(User user)
    {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return null;
    }

    @Override
    public String getPassword()
    {
        return user.getPassword();
    }

    @Override
    public String getUsername()
    {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return user.isEnabled();
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return user.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return user.isEnabled();
    }

    @Override
    public boolean isEnabled()
    {
        return user.isEnabled();
    }


}
