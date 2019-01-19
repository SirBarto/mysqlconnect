package com.example.demo.controller;

import com.example.demo.model.Beer;
import com.example.demo.repository.BeerRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.BeerService;
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

    @Autowired
    CommentRepository commentRepository;

    @PostMapping(path = "/beer/add")
    public @ResponseBody
    ResponseEntity<Void> addNewBeer(Beer beer) {
        Beer savedBeer = beerRepository.save(beer);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedBeer.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/beer/{id}")
    public ResponseEntity<Beer> getBeer(@PathVariable("id") Long id) {
        Beer n = beerService.getBeerById(id);
        return new ResponseEntity<Beer>(n, HttpStatus.OK);
    }

    @GetMapping(path = "/beer/all")
    public ResponseEntity<List<Beer>> getAllBeers() {
        List<Beer> list = beerService.getAllBeers();
        return new ResponseEntity<List<Beer>>(list, HttpStatus.OK);
    }

    @DeleteMapping(path = "/beer/remove/{id}")
    public ResponseEntity<Void> deleteBeer(@PathVariable("id") Long id) {
        beerService.deleteBeer(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(path = "/beer/update/{id}")
    public ResponseEntity<Beer> updateBeer(@RequestBody Beer beer, @PathVariable Long id) {
        Optional<Beer> beerOptional = beerRepository.findById(id);
        if (!beerOptional.isPresent())
            return ResponseEntity.notFound().build();
        beer.setId(id);
        beerRepository.save(beer);
        return ResponseEntity.noContent().build();
    }

}
