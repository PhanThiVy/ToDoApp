package com.example.ToDoAppDemo.repository;

import com.example.ToDoAppDemo.model.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList,Long> {
}
