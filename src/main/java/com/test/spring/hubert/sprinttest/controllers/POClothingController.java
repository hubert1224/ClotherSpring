package com.test.spring.hubert.sprinttest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.test.spring.hubert.sprinttest.models.ClothingCategory;
import com.test.spring.hubert.sprinttest.models.POClothing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.bind.annotation.*;
import com.test.spring.hubert.sprinttest.DAOs.POClothingDAO;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
public class POClothingController
{
    private POClothingDAO dao;

    @Autowired
    public POClothingController(POClothingDAO dao)
    {
        this.dao = dao;
    }

    @RequestMapping(path = "/api/poclothing",method = RequestMethod.POST)
    public List<POClothing> save(Principal principal, @RequestBody ObjectNode data) //Map of object to Save and Category to display
    {
        ObjectMapper jsonMapper = new ObjectMapper();
        POClothing parsedToSave = new POClothing();
        ClothingCategory parsedSearchCriteria = new ClothingCategory();
        try
        {
            parsedToSave = jsonMapper.treeToValue(data.get("toSave"), POClothing.class);
            parsedSearchCriteria = jsonMapper.treeToValue(data.get("toSearch"), ClothingCategory.class);
        }
        catch(JsonProcessingException e)
        {
            System.out.println("Couldn't parse toSave or SearchCriteria(Category) from json in POClothingController.save");
            System.out.print(e.getMessage());
        }

        dao.save(parsedToSave);
        return dao.findAllByOwnerLoginAndCategory(principal.getName(),parsedSearchCriteria);
    }

    @RequestMapping(path="/api/poclothing",method = RequestMethod.DELETE)
    public List<POClothing> deleteById(Principal principal, @RequestParam Map<String,String> data)
    {
        ObjectMapper jsonMapper = new ObjectMapper();
        ClothingCategory category = new ClothingCategory();
        POClothing parsedToDelete = new POClothing();
        try
        {
            parsedToDelete = jsonMapper.readValue(data.get("toDelete"), POClothing.class);
            category = jsonMapper.readValue(data.get("toSearch"),ClothingCategory.class);
        }
        catch(IOException e)
        {
            System.out.println("Couldn't parse toDelete from json in POClothingController.deleteById");
            System.err.print(e.getMessage());
        }
        dao.deleteById(parsedToDelete.getId());
        return findAllByOwnerNameAndCategory(principal,data);
    }

    @RequestMapping(path = "/api/poclothing",method = RequestMethod.GET)
    public List<POClothing> findAllByOwnerNameAndCategory(Principal principal, @RequestParam Map<String,String> data)
    {
        ObjectMapper jsonMapper = new ObjectMapper();
        ClothingCategory parsedSearchCriteria = new ClothingCategory();
        try
        {

            parsedSearchCriteria = jsonMapper.readValue(data.get("toSearch"), ClothingCategory.class);
        }
        catch(IOException e)
        {
            System.out.println("Couldn't parse toSave or SearchCriteria(Category) from json in POClothingController.save");
            System.err.print(e.getMessage());
        }

        return dao.findAllByOwnerLoginAndCategory(principal.getName(),parsedSearchCriteria);
    }

    @RequestMapping(path = "/api/poclothing",method = RequestMethod.PUT)
    public List<POClothing> update(Principal principal, @RequestBody ObjectNode data)
    {
        ObjectMapper jsonMapper = new ObjectMapper();
        ClothingCategory parsedSearchCriteria = new ClothingCategory();
        POClothing updateData = new POClothing();
        try
        {
            updateData = jsonMapper.treeToValue(data.get("toUpdate"),POClothing.class);
            parsedSearchCriteria = jsonMapper.treeToValue(data.get("toSearch"), ClothingCategory.class);
        }
        catch(JsonProcessingException e)
        {
            System.out.println("Couldn't parse updateData or SearchCriteria(Category) from json in POClothingController.update");
            System.err.print(e.getMessage());
        }

        dao.update(updateData.getId(),updateData.getName(),updateData.getWornTimeHours(),updateData.getCategory(),updateData.getState());
        return dao.findAllByOwnerLoginAndCategory(principal.getName(),parsedSearchCriteria);
    }
}
