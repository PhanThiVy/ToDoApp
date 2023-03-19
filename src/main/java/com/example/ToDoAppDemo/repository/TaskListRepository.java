package com.example.ToDoAppDemo.repository;

import com.example.ToDoAppDemo.model.TaskList;
import com.example.ToDoAppDemo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList,Long> {
    @Query(value = "SELECT * FROM tasK_list r WHERE r.list_name = :taskListName  and r.user_id= :userId", nativeQuery = true)
    Optional<TaskList> listNameIsExistForAdd(@Param("taskListName") String taskListName, @Param("userId") Long userId);
    @Query(value = "SELECT * FROM tasK_list r WHERE r.list_name = :taskListName and r.tasK_list_id != :taskListId and r.user_id= :userId", nativeQuery = true)
    Optional<TaskList> listNameIsExistForEdit(@Param("taskListName") String taskListName, @Param("taskListId") Long taskListId,@Param("userId") Long userId);

//    public Page<TaskList> findAll(Pageable Page);
    public Page<TaskList> findByUser(Pageable Page,User user);
}
