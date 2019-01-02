package com.example.demo.Service;

import com.example.demo.model.Comment;

import java.util.List;

public interface ICommentService {
    List<Comment> getAllComments();
    Comment getCommentById(int id);
    void addNewComment(Comment comment);
    void updateComment(Comment comment);
    void deleteComment(int id);
}
