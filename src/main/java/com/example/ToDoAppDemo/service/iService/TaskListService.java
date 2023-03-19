package com.example.ToDoAppDemo.service.iService;

import com.example.ToDoAppDemo.dto.requestDto.TaskListRequestDto;
import com.example.ToDoAppDemo.dto.responseDto.TaskListResponseDto;
import com.example.ToDoAppDemo.model.TaskList;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

public interface TaskListService {
    public TaskListResponseDto addTaskList(TaskListRequestDto taskListRequestDto);
    public void taskListNameIsExistForAdd(String taskListName,Long userId);
    public TaskList getTaskList(Long taskListById);
    public TaskListResponseDto getTaskListById(Long taskListById);
    public Page<TaskListResponseDto> getAllTaskList(int pageNumber, Long userId);
    public TaskListResponseDto editTaskList(Long taskListId, TaskListRequestDto taskListRequestDto);
    public void taskListNameIsExistForEdit(String taskListName,Long taskListId,Long userId);
}
