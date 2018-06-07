package com.test.spring.hubert.sprinttest.DAOs;

import com.test.spring.hubert.sprinttest.models.ClothingCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothingCategoryDAO extends JpaRepository<ClothingCategory,Long>
{
    List<ClothingCategory> findAll();
    ClothingCategory save(ClothingCategory toSave);
    void delete(ClothingCategory toDelete);
}
