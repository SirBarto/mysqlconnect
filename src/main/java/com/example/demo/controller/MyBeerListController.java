package com.example.demo.controller;

import com.example.demo.model.MyBeerList;
import com.example.demo.model.User;
import com.example.demo.repository.BeerRepository;
import com.example.demo.repository.MyBeerListRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

//import com.example.demo.service.MyBeerListService;

@RestController
@RequestMapping(path = "/demo")
public class MyBeerListController {

    @Autowired
    MyBeerListRepository myBeerListRepository;

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/{userId}/best")
    public Page<MyBeerList> getAllFavouriteBeerByUserId(@PathVariable(value = "userId") Long userId, Pageable pageable) {
        Optional<User> optionalMyBeerList = userRepository.findById(userId);
        beerRepository.findAll();
        if (optionalMyBeerList.isPresent()) {
            return myBeerListRepository.findByUserId(optionalMyBeerList.get(), pageable);
        }
        return null;
    }

    @PostMapping("/user/{userId}/best/{beerId}")
    public MyBeerList addBestBeer(@PathVariable(value = "userId") Long userId,
                                  @PathVariable(value = "beerId") Long beerId,
                                  @Valid @RequestBody MyBeerList myBeerList) {

        return userRepository.findById(userId).map(user -> {
            myBeerList.setUserId(user);
            myBeerList.setBeerId(beerRepository.findById(beerId).get());
            return myBeerListRepository.save(myBeerList);
        }).orElseThrow(() -> new ResourceNotFoundException("BeerId " + beerId + " not found"));
    }

    private MyBeerList getMyBeerListById(Long id) {
        return myBeerListRepository.findById(id).get();
    }

    @DeleteMapping("/user/{userId}/best/{beerId}")
    public ResponseEntity<?> deleteBeerFromList(@PathVariable("userId") Long userId,
                                                @PathVariable("beerId") Long beerId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("UserId " + userId + " not found");
        }

        return myBeerListRepository.findById(beerId).map(myBeerList -> {
            myBeerListRepository.delete(myBeerList);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("BestBeer with id " + beerId + " not found"));
    }

}


/*
    @PostMapping(path = "/mylist/add")
    public ResponseEntity<Void> addNewBeerToMyList(@RequestBody MyBeerList myBeerList) {
        MyBeerList savedBeerOnList = myBeerListRepository.save(myBeerList);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedBeerOnList.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/mylist/{id}")
    public ResponseEntity<MyBeerList> getBeerById(@PathVariable("id") int id) {
        MyBeerList n = myBeerListService.getMyBeerListById(id);
        return new ResponseEntity<MyBeerList>(n, HttpStatus.OK);
    }

    @GetMapping(path = "/mylist/all")
    public ResponseEntity<List<MyBeerList>> getAllBeersFromList() {
        List<MyBeerList> list = myBeerListService.getAllMyBeerList();
        return new ResponseEntity<List<MyBeerList>>(list, HttpStatus.OK);
    }

    @DeleteMapping(path = "/mylist/remove/{id}")
    public ResponseEntity<Void> deleteBeerFromList(@PathVariable("id") int id) {
        myBeerListService.deleteBeerFromMyList(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/mylist/update/{id}")
    public ResponseEntity<MyBeerList> updateBeerOnList(@RequestBody MyBeerList myBeerList, @PathVariable int id) {
        Optional<MyBeerList> commentOptional = myBeerListRepository.findById(id);
        if (!commentOptional.isPresent())
            return ResponseEntity.notFound().build();
        myBeerList.setId(id);
        myBeerListRepository.save(myBeerList);
        return ResponseEntity.noContent().build();
    }
    */