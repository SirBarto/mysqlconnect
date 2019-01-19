package com.example.demo.repository;


import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLoginAndPassword(String login, String password);

//queries in this place
    // List<User> findById(int id);
    // List<User> findDistinctByCategory(String category);
    //  List<User> findByIdAndLogin(String title, String category);
/*
    @Query("SELECT a FROM Article a WHERE a.title=:title and a.category=:category")
    List<User> fetchArticles(@Param("title") String title, @Param("category") String category);
*/
}
