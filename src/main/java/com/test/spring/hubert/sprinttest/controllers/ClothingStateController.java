package com.test.spring.hubert.sprinttest.controllers;

import com.test.spring.hubert.sprinttest.DAOs.ClothingStateDAO;
import com.test.spring.hubert.sprinttest.models.ClothingState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClothingStateController
{
    private ClothingStateDAO dao;

    @Autowired
    public ClothingStateController(ClothingStateDAO dao)
    {
        this.dao = dao;
    }

    @RequestMapping(path = "/api/cstate",method = RequestMethod.POST)
    public ClothingState save(@RequestBody ClothingState toSave)
    {
        return dao.save(toSave);
    }

    @RequestMapping(path="/api/cstate",method = RequestMethod.DELETE)
    public void delete(@RequestBody ClothingState toDelete)
    {
        dao.delete(toDelete);
    }

    @RequestMapping(path = "/api/cstate",method = RequestMethod.GET)
    public List<ClothingState> findAll()
    {
        return dao.findAll();
    }
}
