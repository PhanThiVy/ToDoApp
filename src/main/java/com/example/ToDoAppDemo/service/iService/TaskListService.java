package com.example.ToDoAppDemo.service.iService;

import com.example.ToDoAppDemo.dto.requestDto.TaskListRequestDto;
import com.example.ToDoAppDemo.dto.responseDto.TaskListResponseDto;
import com.example.ToDoAppDemo.model.TaskList;
import org.springframework.stereotype.Service;

public interface TaskListService {
    public TaskListResponseDto addTaskList(TaskListRequestDto taskListRequestDto);
    public void roleNameIsExistForAdd(String taskListName,Long userId);
}
