package com.example.ToDoAppDemo.repository;

import com.example.ToDoAppDemo.model.Task;
import com.example.ToDoAppDemo.model.TaskList;
import com.example.ToDoAppDemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TasKRepository extends JpaRepository<Task,Long> {
    @Query(value = "SELECT * FROM tasK t WHERE t.task_name = :taskName  and t.task_list_id= :taskListId", nativeQuery = true)
    Optional<Task> taskNameIsExistForAdd(@Param("taskName") String taskName, @Param("taskListId") Long taskListId);
    @Query(value = "SELECT * FROM tasK t WHERE t.task_name = :taskName and t.task_id != :taskId  and t.task_list_id= :taskListId", nativeQuery = true)
    Optional<Task> taskNameIsExistForEdit(@Param("taskName") String taskName,@Param("taskId") Long taskId, @Param("taskListId") Long taskListId);
}
