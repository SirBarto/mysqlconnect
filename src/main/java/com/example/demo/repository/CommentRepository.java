package com.example.demo.repository;

import com.example.demo.model.Beer;
import com.example.demo.model.Comment;
import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByBeerId(Beer beerId, Pageable pageable);
    Page<Comment> findByUserId(User userId, Pageable pageable);
}
