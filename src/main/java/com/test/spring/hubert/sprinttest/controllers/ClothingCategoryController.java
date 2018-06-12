package com.test.spring.hubert.sprinttest.controllers;

import com.test.spring.hubert.sprinttest.DAOs.ClothingCategoryDAO;
import com.test.spring.hubert.sprinttest.MyUserPrincipal;
import com.test.spring.hubert.sprinttest.models.ClothingCategory;
import com.test.spring.hubert.sprinttest.models.User;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Map;

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
    public List<ClothingCategory> save(Principal principal, @RequestBody ClothingCategory toSave)
    {
        dao.save(toSave);
        return dao.findAllByOwnerLogin(principal.getName());
    }

    @RequestMapping(path="/api/ccategory",method = RequestMethod.DELETE)
    public List<ClothingCategory> deleteById(Principal principal, @RequestParam Map<String,String> data)
    {
        dao.deleteById(Long.parseLong(data.get("id")));
        return dao.findAllByOwnerLogin(principal.getName());
    }

    @RequestMapping(path = "/api/ccategory",method = RequestMethod.GET)
    public List<ClothingCategory> findAllByOwnerName(Principal principal)
    {
        return dao.findAllByOwnerLogin(principal.getName());
    }

    @RequestMapping(path = "/api/ccategory",method = RequestMethod.PUT)
    public List<ClothingCategory> update(Principal principal, @RequestBody ClothingCategory category)
    {
        dao.update(category.getId(),category.getCategoryName());
        return dao.findAllByOwnerLogin(principal.getName());
    }

}
