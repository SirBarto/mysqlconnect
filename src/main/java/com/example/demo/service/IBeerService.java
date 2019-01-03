package com.example.demo.service;

import com.example.demo.model.Beer;

import java.util.List;

public interface IBeerService {
    List<Beer> getAllBeers();
    Beer getBeerById(Long id);
    void addNewBeer(Beer beer);
    void updateBeer(Beer beer);
    void deleteBeer(Long id);
}
