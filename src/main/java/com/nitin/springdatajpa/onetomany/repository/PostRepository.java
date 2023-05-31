package com.nitin.springdatajpa.onetomany.repository;

import com.nitin.springdatajpa.onetomany.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
