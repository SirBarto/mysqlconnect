package com.example.demo.repository;

import com.example.demo.model.MyBeerList;
import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MyBeerListRepository extends JpaRepository<MyBeerList,Long> {
    Page<MyBeerList> findByUserId(User userId, Pageable pageable);
}
