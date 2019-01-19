package com.example.demo.service;

import com.example.demo.model.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    void addNewUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
}
