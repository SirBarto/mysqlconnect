package com.example.demo.controller;

import com.example.demo.Service.CommentService;
import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RestController
@RequestMapping(path = "/demo")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentService commentService;

    @PostMapping(path = "/comment/add")
    public ResponseEntity<Void> addNewComment(@RequestBody Comment comment) {
        Comment savedComment = commentRepository.save(comment);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedComment.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/comment{id}")
    public ResponseEntity<Comment> getComment(@PathVariable("id") int id) {
        Comment n = commentService.getCommentById(id);
        return new ResponseEntity<Comment>(n, HttpStatus.OK);
    }

    @GetMapping(path = "/comment/all")
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> list = commentService.getAllComments();
        return new ResponseEntity<List<Comment>>(list, HttpStatus.OK);
    }
}
