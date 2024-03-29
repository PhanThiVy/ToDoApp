package com.example.ToDoAppDemo.repository;

import com.example.ToDoAppDemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public Optional<User> findUserByUserName(String userName);
    User findByEmail(String email);
}
