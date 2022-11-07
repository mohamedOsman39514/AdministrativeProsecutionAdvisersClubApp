package com.example.proxy.repository;

import com.example.proxy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u FROM User u WHERE u.username= :username")
    User findByUsername(String username);

}
