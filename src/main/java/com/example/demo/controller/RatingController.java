package com.example.demo.controller;

import com.example.demo.model.Beer;
import com.example.demo.repository.BeerRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.RatingRepository;
import com.example.demo.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/demo")
public class RatingController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    BeerService beerService;

    @Autowired
    RatingRepository ratingRepository;



    @GetMapping("/beer/top10")
    public ResponseEntity<List<Beer>> getAllBeers() {
        List<Beer> list = ratingRepository.findTopByRating();
        return new ResponseEntity<List<Beer>>(list, HttpStatus.OK);
    }

    @GetMapping("/beer/factory")
    public ResponseEntity<List<Beer>> getAllFactories() {
        List<Beer> list = ratingRepository.findFactoryFromBeer();
        return new ResponseEntity<List<Beer>>(list, HttpStatus.OK);
    }

    @GetMapping("/beer/factory/{factory}")
    public ResponseEntity<List<Beer>> getBestFromFactorie(@PathVariable("factory")String factory) {
        List<Beer> list = ratingRepository.findBestBeerByFactory(factory);
        return new ResponseEntity<List<Beer>>(list, HttpStatus.OK);
    }

/*
    @GetMapping("/beer/top10")
    public ResponseEntity<List<Beer>> getAllBeers(){
        List<Beer> list = ratingRepository.findTop2OrderByRating();
        return new ResponseEntity<List<Beer>>(list, HttpStatus.OK);
    }*/

}
