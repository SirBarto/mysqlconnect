package com.example.demo.Service;

import com.example.demo.model.Beer;

import java.util.List;

public interface IBeerService {
    List<Beer> getAllBeers();
    Beer getBeerById(int id);
    void addNewBeer(Beer beer);
    void updateBeer(Beer beer);
    void deleteBeer(int id);
}
