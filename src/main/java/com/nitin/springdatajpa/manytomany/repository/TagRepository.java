package com.nitin.springdatajpa.manytomany.repository;

import com.nitin.springdatajpa.manytomany.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
