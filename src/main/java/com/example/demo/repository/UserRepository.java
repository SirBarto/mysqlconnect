package com.example.demo.repository;


import com.example.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import retrofit2.http.Query;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
//queries in this place
   // List<User> findById(int id);
   // List<User> findDistinctByCategory(String category);
  //  List<User> findByIdAndLogin(String title, String category);
/*
    @Query("SELECT a FROM Article a WHERE a.title=:title and a.category=:category")
    List<User> fetchArticles(@Param("title") String title, @Param("category") String category);
*/
}
