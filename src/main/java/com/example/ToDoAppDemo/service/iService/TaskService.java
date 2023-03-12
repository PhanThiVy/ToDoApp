package com.example.ToDoAppDemo.service.iService;

import com.example.ToDoAppDemo.dto.requestDto.TaskRequestDto;
import com.example.ToDoAppDemo.dto.responseDto.TaskResponseDto;

public interface TaskService {
    public TaskResponseDto addTask(String taskListId,TaskRequestDto taskRequestDto);
    public void taskNameIsExistForAdd(String taskName,Long taskListId);
}
