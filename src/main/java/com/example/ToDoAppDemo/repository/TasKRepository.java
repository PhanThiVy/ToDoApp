package com.example.ToDoAppDemo.repository;

import com.example.ToDoAppDemo.model.Task;
import com.example.ToDoAppDemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasKRepository extends JpaRepository<Task,Long> {

}
