package com.example.demo.controller;

import com.example.demo.Service.BeerService;
import com.example.demo.model.Beer;
import com.example.demo.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/demo")
public class BeerController {
    @Autowired
    BeerRepository beerRepository;

    @Autowired
    BeerService beerService;

    @PostMapping(path = "/beer/add")
    public ResponseEntity<Void> addNewBeer(@RequestBody Beer beer) {
        Beer savedBeer = beerRepository.save(beer);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedBeer.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/beer/{id}")
    public ResponseEntity<Beer> getBeer(@PathVariable("id") int id) {
        Beer n = beerService.getBeerById(id);
        return new ResponseEntity<Beer>(n, HttpStatus.OK);
    }

    @GetMapping(path = "/beer/all")
    public ResponseEntity<List<Beer>> getAllBeers() {
        List<Beer> list = beerService.getAllBeers();
        return new ResponseEntity<List<Beer>>(list, HttpStatus.OK);
    }

    @DeleteMapping(path = "/beer/remove/{id}")
    public ResponseEntity<Void> deleteBeer(@PathVariable("id") int id) {
        beerService.deleteBeer(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/beer/update/{id}")
    public ResponseEntity<Beer> updateBeer(@RequestBody Beer beer, @PathVariable int id) {
        Optional<Beer> beerOptional = beerRepository.findById(id);
        if (!beerOptional.isPresent())
            return ResponseEntity.notFound().build();
        beer.setId(id);
        beerRepository.save(beer);
        return ResponseEntity.noContent().build();
    }

}
