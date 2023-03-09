package com.example.ToDoAppDemo.service.serviceDetails;

import com.example.ToDoAppDemo.dto.requestDto.TaskListRequestDto;
import com.example.ToDoAppDemo.dto.responseDto.TaskListResponseDto;
import com.example.ToDoAppDemo.exception.taskListException.TaskListNameIsExisException;
import com.example.ToDoAppDemo.model.TaskList;
import com.example.ToDoAppDemo.repository.TaskListRepository;
import com.example.ToDoAppDemo.service.iService.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskListServiceImpl implements TaskListService {
    private final TaskListRepository taskListRepository;
    @Override
    public TaskListResponseDto addTaskList(TaskListRequestDto taskListRequestDto) {
        return null;
    }

    @Override
    public void roleNameIsExistForAdd(String taskListName, Long userId) {
        TaskList taskList = taskListRepository.listNameIsExistForAdd(taskListName,userId);
        if(taskList!=null){
            throw new TaskListNameIsExisException(HttpStatus.CONFLICT.value(), " This task list name is exist - please enter a new one");
        }
    }
}
