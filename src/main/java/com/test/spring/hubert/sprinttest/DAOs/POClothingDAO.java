package com.test.spring.hubert.sprinttest.DAOs;

import com.test.spring.hubert.sprinttest.models.ClothingCategory;
import com.test.spring.hubert.sprinttest.models.POClothing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface POClothingDAO extends JpaRepository<POClothing,Long>
{
    List<POClothing> findAllByOwnerLoginAndCategory(String ownerId, ClothingCategory category);
    POClothing save(POClothing toSave);
    void deleteById(long id);

    @Transactional
    @Modifying
    @Query("UPDATE POClothing SET name = ?2, wornTimeHours = ?3, category = ?4, state = ?5 where id = ?1")
    void update(long id, String name, double wornTimeHours, ClothingCategory category, String state);

}
