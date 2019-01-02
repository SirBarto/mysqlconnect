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
import java.util.Optional;

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

    @DeleteMapping(path = "/comment/remove/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") int id) {
        commentService.deleteComment(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/comment/update/{id}")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment, @PathVariable int id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (!commentOptional.isPresent())
            return ResponseEntity.notFound().build();
        comment.setId(id);
        commentRepository.save(comment);
        return ResponseEntity.noContent().build();
    }
}
