package com.example.demo.service;

import com.example.demo.model.Beer;
import com.example.demo.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BeerService implements IBeerService {

    final
    BeerRepository beerRepository;

    @Autowired
    public BeerService(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public Beer getBeerById(Long id) {
        return beerRepository.findById(id).get();
    }

    @Override
    public void addNewBeer(Beer beer) {
        beerRepository.save(beer);
    }

    @Override
    public void updateBeer(Beer beer) {
        beerRepository.save(beer);
    }

    @Override
    public void deleteBeer(Long id) {
        beerRepository.delete(getBeerById(id));
    }

    @Override
    public List<Beer> getAllBeers() {
        List<Beer> list = new ArrayList<>();
        beerRepository.findAll().forEach(list::add);
        return list;
    }



}
