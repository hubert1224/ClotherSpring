package com.test.spring.hubert.sprinttest.DAOs;

import com.test.spring.hubert.sprinttest.models.ClothingState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClothingStateDAO extends JpaRepository<ClothingState,Long>
{
    List<ClothingState> findAll();
    ClothingState save(ClothingState toSave);
    void delete(ClothingState toDelete);
}
