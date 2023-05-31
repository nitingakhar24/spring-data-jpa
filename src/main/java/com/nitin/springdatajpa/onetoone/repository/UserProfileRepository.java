package com.nitin.springdatajpa.onetoone.repository;

import com.nitin.springdatajpa.onetoone.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
