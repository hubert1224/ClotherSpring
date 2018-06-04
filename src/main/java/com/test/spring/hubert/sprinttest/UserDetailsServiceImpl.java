package com.test.spring.hubert.sprinttest;

import com.test.spring.hubert.sprinttest.DAOs.UserDAO;

import com.test.spring.hubert.sprinttest.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private final UserDAO dao;

    @Autowired
    public UserDetailsServiceImpl(UserDAO dao)
    {
        this.dao = dao;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = dao.findByLogin(username);
        if(user == null)
        {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }
}
