package com.sparta.preonboarding.repository;

import com.sparta.preonboarding.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
