package com.example.ToDoAppDemo.service.iService;

import com.example.ToDoAppDemo.dto.requestDto.TaskListRequestDto;
import com.example.ToDoAppDemo.dto.requestDto.TaskRequestDto;
import com.example.ToDoAppDemo.dto.responseDto.TaskListResponseDto;
import com.example.ToDoAppDemo.dto.responseDto.TaskResponseDto;
import com.example.ToDoAppDemo.model.Task;

public interface TaskService {
    public TaskResponseDto addTask(String taskListId,TaskRequestDto taskRequestDto);
    public void taskNameIsExistForAdd(String taskName,Long taskListId);

    public Task getTask(String taskId);
    public TaskResponseDto getTaskById(String taskId);
    public TaskResponseDto editTask(String taskId,String taskListId, TaskRequestDto taskRequestDto);
    public void taskNameIsExistForEdit(String taskName,Long taskId,Long taskListId);
}
