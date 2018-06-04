package com.test.spring.hubert.sprinttest.controllers;

import com.test.spring.hubert.sprinttest.DAOs.UserDAO;
import com.test.spring.hubert.sprinttest.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@RestController
@Validated
public class UserController
{

    private UserDAO dao;

    @Autowired
    public UserController(UserDAO dao)
    {
        this.dao = dao;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST,path="/api/register")
    public User save(@RequestBody User saved)
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
        saved.setPassword(encoder.encode(saved.getPassword()));
        return dao.save(saved);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET,path="/api/users")
    public List<User> findAll()
    {
        return dao.findAll();
    }

    @CrossOrigin
    @RequestMapping(path = "/api/login")
    public User user(@RequestBody User user)
    {
        return user;
    }

    @ExceptionHandler(value = { DataIntegrityViolationException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleDBConsViolation(DataIntegrityViolationException e, HttpServletResponse response)
    {
        return e.getMessage();
    }
}
