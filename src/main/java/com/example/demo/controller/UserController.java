package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
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
@RequestMapping(path = "/demo", method = RequestMethod.GET)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping(path = "/user/add")
    public @ResponseBody
    ResponseEntity<Void> addNewUser(User user) {
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        User n = userService.getUserById(id);
        return new ResponseEntity<User>(n, HttpStatus.OK);
    }

    @GetMapping(path = "/user/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = userService.getAllUsers();
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }

    @DeleteMapping(path = "/user/remove/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(path = "/user/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent())
            return ResponseEntity.notFound().build();
        user.setId(id);
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }

/*
    @GetMapping(path = "/user/login/")
    public ResponseEntity loginUser(
            @RequestBody User user) {


        return new ResponseEntity("18",HttpStatus.OK);
    }
*/

}





    /*
        @GetMapping(path = "/add")
        public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email){
            User n = new User();
            n.setName(name);
            n.setEmail(email);
            userRepository.save(n);
            return "201";
        }
    */