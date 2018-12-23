package com.example.demo.repository;

import com.example.demo.model.Beer;
import org.springframework.data.repository.CrudRepository;

public interface BeerRepository extends CrudRepository<Beer,Integer> {
}
