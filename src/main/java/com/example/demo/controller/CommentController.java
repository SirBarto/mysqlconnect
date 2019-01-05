package com.example.demo.controller;

import com.example.demo.model.Beer;
import com.example.demo.model.Comment;
import com.example.demo.repository.BeerRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping(path = "/demo")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/beer/{beerId}/comments")
    public Page<Comment> getAllCommentsByBeerId(@PathVariable(value = "beerId") Long beerId, Pageable pageable) {
        Optional<Beer> optionalBeer = beerRepository.findById(beerId);
        userRepository.findAll();
        if (optionalBeer.isPresent()) {
            return commentRepository.findByBeerId(optionalBeer.get(), pageable);
        }
        return null;
    }

    /*to działa !! version bez polaczenia z tabela user
        @PostMapping("/beer/{beerId}/comments")
        public Comment createComment(@PathVariable(value = "beerId") Long beerId,
                                     @Valid @RequestBody Comment comment) {
            return beerRepository.findById(beerId).map(post -> {
                comment.setBeerId(post);
                return commentRepository.save(comment);
            }).orElseThrow(() -> new ResourceNotFoundException("BeerId " + beerId + " not found"));
        }
    */
    @PostMapping("/beer/{beerId}/user/{userId}/comments")
    public Comment createComment(@PathVariable(value = "beerId") Long beerId,
                                 @PathVariable(value = "userId") Long userId,
                                 @Valid @RequestBody Comment comment) {
        return beerRepository.findById(beerId).map(beer -> {
            comment.setBeerId(beer);
            comment.setUserId(userRepository.findById(userId).get());
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("BeerId " + beerId + " not found"));
    }

    @PatchMapping(path = "/beer/{beerId}/user/{userId}/comments/{comId}")
    public Comment updateComment(@PathVariable(value = "beerId") Long beerId,
                                 @PathVariable(value = "userId") Long userId,
                                 @PathVariable(value = "comId") Long comId,
                                 @Valid @RequestBody Comment comment) {
        if (!beerRepository.existsById(beerId)) {
            throw new ResourceNotFoundException("BeerId" + beerId + " not found");
        }

        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("UserId" + userId + " not found");
        }

        return commentRepository.findById(comId).map(comments -> {
            comments.setText(comment.getText());
            comments.setRating(comment.getRating());
            return commentRepository.save(comments);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId" + comId + " not found"));
    }

    @DeleteMapping(path = "/beer/{beerId}/comments/{comId}")
    public ResponseEntity<?> deleteComment(@PathVariable("beerId") Long beerId,
                                           @PathVariable(value = "comId") Long comId) {
        if (!beerRepository.existsById(beerId)) {
            throw new ResourceNotFoundException("BeerId" + beerId + " not found");
        }

        return commentRepository.findById(comId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + comId + " not found"));
    }


/*
    @Autowired
    CommentService commentService;

    @PostMapping(path = "/comment/add")
    public ResponseEntity<Void> addNewComment(@RequestBody Comment comment) {
        Comment savedComment = commentRepository.save(comment);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedComment.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/beer/{id}/comment")
    public ResponseEntity<Comment> getComment(@PathVariable("id") int id) {
        Comment n = commentService.getCommentByBeerId(id);
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
    */

/* to je dobre
    @PostMapping("/beer/{beerId}/comments")
    public Comment createComment(@PathVariable(value = "beerId") Long beerId,
                                 @Valid @RequestBody Comment comment) {
        return beerRepository.findById(beerId).map(post -> {
            comment.setBeerId(post);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("BeerId " + beerId + " not found"));
    }

    @PatchMapping(path = "/beer/{beerId}/comments/{comId}")
    public Comment updateComment(@PathVariable(value = "beerId") Long beerId,
                                 @PathVariable(value = "comId") Long comId,
                                 @Valid @RequestBody Comment comment) {
        if (!beerRepository.existsById(beerId)) {
            throw new ResourceNotFoundException("BeerId" + beerId + " not found");
        }

        return commentRepository.findById(comId).map(comments -> {
            comments.setText(comment.getText());
            comments.setRating(comment.getRating());
            return commentRepository.save(comments);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId" + comId + " not found"));
    }
 */
}
