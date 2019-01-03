package com.example.demo.controller;

import com.example.demo.model.MyBeerList;
import com.example.demo.repository.MyBeerListRepository;
import com.example.demo.service.MyBeerListService;
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
@RestController
@RequestMapping(path = "/demo")
public class MyBeerListController {

    @Autowired
    MyBeerListRepository myBeerListRepository;

    @Autowired
    MyBeerListService myBeerListService;

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
}
