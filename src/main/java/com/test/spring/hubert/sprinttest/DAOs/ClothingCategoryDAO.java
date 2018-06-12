package com.test.spring.hubert.sprinttest.DAOs;

import com.test.spring.hubert.sprinttest.models.ClothingCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public interface ClothingCategoryDAO extends JpaRepository<ClothingCategory,Long>
{
    List<ClothingCategory> findAllByOwnerLogin(String ownerName);
    ClothingCategory save(ClothingCategory toSave);
    List<ClothingCategory> deleteById(long id);

    @Transactional
    @Modifying
    @Query("update ClothingCategory cc set cc.categoryName = ?2 where cc.id = ?1")
    void update(long id, String categoryName);
}
