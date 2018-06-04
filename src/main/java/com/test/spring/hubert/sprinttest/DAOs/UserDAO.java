package com.test.spring.hubert.sprinttest.DAOs;

import com.test.spring.hubert.sprinttest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User,Long>
{
    User findById(int id);
    User save(User saved);
    User findByLogin(String login);
    List<User> findAll();
}
