package com.example.demo.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(int id) {
        User obj = userRepository.findById(id).get();
        return obj;
    }

    //TODO addUser component
   /* @Override
    public boolean addUser(User user) {
        List<User> list = userRepository
        if (list.size() > 0) {
            return false;
        } else {
            userRepository.save(user);
            return true;
    }*/
    @Override
    public void addNewUser(User user) {
         userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.delete(getUserById(id));
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().forEach(list::add);
        return list;
    }

}
