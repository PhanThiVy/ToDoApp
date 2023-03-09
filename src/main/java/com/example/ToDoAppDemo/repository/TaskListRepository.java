package com.example.ToDoAppDemo.repository;

import com.example.ToDoAppDemo.model.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList,Long> {
    @Query(value = "SELECT * FROM tasK_list r WHERE r.list_name = :taskListName  and r.user_id= :userId", nativeQuery = true)
    TaskList listNameIsExistForAdd(@Param("taskListName") String taskListName, @Param("userId") Long userId);
}
