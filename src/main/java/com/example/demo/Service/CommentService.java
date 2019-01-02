package com.example.demo.Service;

import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService implements ICommentService{

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment getCommentById(int id) {
        Comment obj = commentRepository.findById(id).get();
        return obj;
    }

    @Override
    public void addNewComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void updateComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(int id) {
        commentRepository.delete(getCommentById(id));
    }

    @Override
    public List<Comment> getAllComments() {
        List<Comment> list = new ArrayList<>();
        commentRepository.findAll().forEach(e -> list.add(e));
        return list;
    }
}
