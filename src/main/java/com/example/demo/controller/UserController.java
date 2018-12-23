package com.example.demo.controller;

import com.example.demo.Service.UserService;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
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
    @PostMapping(path = "/user/add")
    public ResponseEntity<Void> addNewUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        User n = userService.getUserById(id);
        return new ResponseEntity<User>(n, HttpStatus.OK);
    }

    @GetMapping(path = "/user/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = userService.getAllUsers();
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }

    @DeleteMapping(path = "/user/remove/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/user/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent())
            return ResponseEntity.notFound().build();
        user.setId(id);
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }


}
