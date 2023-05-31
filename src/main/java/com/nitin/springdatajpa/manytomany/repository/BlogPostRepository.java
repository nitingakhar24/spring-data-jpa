package com.nitin.springdatajpa.manytomany.repository;

import com.nitin.springdatajpa.manytomany.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
}
