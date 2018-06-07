package com.test.spring.hubert.sprinttest.controllers;

import com.test.spring.hubert.sprinttest.DAOs.ClothingCategoryDAO;
import com.test.spring.hubert.sprinttest.models.ClothingCategory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClothingCategoryController
{
    private final ClothingCategoryDAO dao;

    @Autowired
    public ClothingCategoryController(ClothingCategoryDAO dao)
    {
        this.dao = dao;
    }

    @RequestMapping(path = "/api/ccategory",method = RequestMethod.POST)
    public ClothingCategory save(@RequestBody ClothingCategory toSave)
    {
        return dao.save(toSave);
    }

    @RequestMapping(path="/api/ccategory",method = RequestMethod.DELETE)
    public void delete(@RequestBody ClothingCategory toDelete)
    {
        dao.delete(toDelete);
    }

    @RequestMapping(path = "/api/ccategory",method = RequestMethod.GET)
    public List<ClothingCategory> findAll()
    {
        return dao.findAll();
    }
}
