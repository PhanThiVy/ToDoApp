package com.example.ToDoAppDemo.service.iService;

import com.example.ToDoAppDemo.dto.requestDto.TaskListRequestDto;
import com.example.ToDoAppDemo.dto.responseDto.TaskListResponseDto;
import com.example.ToDoAppDemo.model.TaskList;
import org.springframework.stereotype.Service;

public interface TaskListService {
    public TaskListResponseDto addTaskList(String userId,TaskListRequestDto taskListRequestDto);
    public void taskListNameIsExistForAdd(String taskListName,Long userId);
    public TaskList getTaskList(String taskListById);
    public TaskListResponseDto getTaskListById(String taskListById);
    public TaskListResponseDto editTaskList(String taskListId, TaskListRequestDto taskListRequestDto);
    public void taskListNameIsExistForEdit(String taskListName,Long taskListId,Long userId);
}
