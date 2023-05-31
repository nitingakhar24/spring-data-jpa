package com.nitin.springdatajpa.onetomany.repository;

import com.nitin.springdatajpa.onetomany.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
