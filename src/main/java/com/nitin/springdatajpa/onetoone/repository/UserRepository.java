package com.nitin.springdatajpa.onetoone.repository;

import com.nitin.springdatajpa.onetoone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
