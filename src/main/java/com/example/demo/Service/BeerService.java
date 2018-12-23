package com.example.demo.Service;

import com.example.demo.model.Beer;
import com.example.demo.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BeerService implements IBeerService {

    @Autowired
    BeerRepository beerRepository;

    @Override
    public Beer getBeerById(int id) {
        Beer obj = beerRepository.findById(id).get();
        return obj;
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
    public void deleteBeer(int id) {
        beerRepository.delete(getBeerById(id));
    }

    @Override
    public List<Beer> getAllBeers() {
        List<Beer> list = new ArrayList<>();
        beerRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

}
