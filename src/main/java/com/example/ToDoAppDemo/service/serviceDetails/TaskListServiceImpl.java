package com.example.ToDoAppDemo.service.serviceDetails;

import com.example.ToDoAppDemo.dto.mapper.Mapper;
import com.example.ToDoAppDemo.dto.requestDto.TaskListRequestDto;
import com.example.ToDoAppDemo.dto.responseDto.TaskListResponseDto;
import com.example.ToDoAppDemo.exception.taskListException.TaskListNameIsExisException;
import com.example.ToDoAppDemo.model.TaskList;
import com.example.ToDoAppDemo.model.User;
import com.example.ToDoAppDemo.repository.TaskListRepository;
import com.example.ToDoAppDemo.repository.UserRepository;
import com.example.ToDoAppDemo.service.iService.TaskListService;
import com.example.ToDoAppDemo.service.iService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskListServiceImpl implements TaskListService {
    private final TaskListRepository taskListRepository;
    private final UserService userService;
    @Override
    public TaskListResponseDto addTaskList(TaskListRequestDto taskListRequestDto) {
        TaskList taskList = new TaskList();
        //check user is exist
        User user = userService.getUser(taskListRequestDto.getUserId());
        //check task list name is exist
        Long userId = Long.valueOf(taskListRequestDto.getUserId());
        roleNameIsExistForAdd(taskListRequestDto.getTaskListName(),userId);

        taskList.setListName(taskListRequestDto.getTaskListName());
        taskList.setUser(user);
        //save task list
        taskListRepository.save(taskList);
        //add task list to user
        userService.addTaskList(user,taskList);
        return Mapper.TaskListToTaskListResponseDto(taskList);
    }

    @Override
    public void roleNameIsExistForAdd(String taskListName, Long userId) {
        TaskList taskList = taskListRepository.listNameIsExistForAdd(taskListName,userId);
        if(taskList!=null){
            throw new TaskListNameIsExisException(HttpStatus.CONFLICT.value(), " This task list name is exist - please enter a new one");
        }
    }

}
