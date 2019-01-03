package com.example.demo.service;

import com.example.demo.model.MyBeerList;

import java.util.List;

public interface IMyBeerListService {
    List<MyBeerList> getAllMyBeerList();
    MyBeerList getMyBeerListById(int id);
    void addNewBeerToMyList(MyBeerList myBeerList);
    void updateBeerOnMyList(MyBeerList myBeerList);
    void deleteBeerFromMyList(int id);
}
