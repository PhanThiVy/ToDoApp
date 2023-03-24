package com.example.ToDoAppDemo.service;

import com.example.ToDoAppDemo.dto.requestDto.TaskListRequestDto;
import com.example.ToDoAppDemo.dto.requestDto.TaskRequestDto;
import com.example.ToDoAppDemo.dto.responseDto.TaskListResponseDto;
import com.example.ToDoAppDemo.dto.responseDto.TaskResponseDto;
import com.example.ToDoAppDemo.model.Task;

public interface TaskService {
    public TaskResponseDto addTask(TaskRequestDto taskRequestDto);
    public void taskNameIsExistForAdd(String taskName,Long taskListId);

    public Task getTask(Long taskId);
    public TaskResponseDto getTaskById(Long taskId);
    public TaskResponseDto editTask(Long taskId, TaskRequestDto taskRequestDto);
    public void taskNameIsExistForEdit(String taskName,Long taskId,Long taskListId);
}
