package com.example.ToDoAppDemo.service.serviceDetails;

import com.example.ToDoAppDemo.dto.mapper.Mapper;
import com.example.ToDoAppDemo.dto.requestDto.TaskListRequestDto;
import com.example.ToDoAppDemo.dto.responseDto.TaskListResponseDto;
import com.example.ToDoAppDemo.exception.taskListException.TaskListNameIsExisException;
import com.example.ToDoAppDemo.exception.taskListException.TaskListNotFoundException;
import com.example.ToDoAppDemo.exception.userException.UserNotFoundException;
import com.example.ToDoAppDemo.jwt.JwtAuthenticationFilter;
import com.example.ToDoAppDemo.jwt.JwtTokenProvider;
import com.example.ToDoAppDemo.model.TaskList;
import com.example.ToDoAppDemo.model.User;
import com.example.ToDoAppDemo.repository.TaskListRepository;
import com.example.ToDoAppDemo.repository.UserRepository;
import com.example.ToDoAppDemo.service.iService.TaskListService;
import com.example.ToDoAppDemo.service.iService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskListServiceImpl implements TaskListService {
    private final TaskListRepository taskListRepository;
    private final UserService userService;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    public TaskListResponseDto addTaskList(TaskListRequestDto taskListRequestDto) {


        TaskList taskList = new TaskList();
        //check user is exist
        User user = userService.getUser(taskListRequestDto.getUserId());
        //check task list name is exist
        taskListNameIsExistForAdd(taskListRequestDto.getTaskListName(), taskListRequestDto.getUserId());

        taskList.setListName(taskListRequestDto.getTaskListName());
        taskList.setUser(user);
        //save task list
        taskListRepository.save(taskList);
        return Mapper.taskListToTaskListResponseDto(taskList);
    }


    @Override
    public void taskListNameIsExistForAdd(String taskListName, Long userId) {
        Optional<TaskList> taskList = taskListRepository.listNameIsExistForAdd(taskListName, userId);
        if (taskList.isPresent()) {
            throw new TaskListNameIsExisException(HttpStatus.CONFLICT.value(), " This task list name is exist - please enter a new one");
        }
    }

    @Override
    public TaskList getTaskList(Long taskListById) {

            //check role is exist
            Optional<TaskList> taskList = taskListRepository.findById(taskListById);
            if(taskList.isEmpty()){
                throw new TaskListNotFoundException(HttpStatus.NOT_FOUND.value(), "Can not find task list with id " + taskListById);
            }
            return taskList.get();
        }
        //if roleId is not number , thrown NotFoundException

    @Override
    public TaskListResponseDto getTaskListById(Long taskListById) {
        TaskList taskList = getTaskList(taskListById);
        return Mapper.taskListToTaskListResponseDto(taskList);
    }

    @Override
    public TaskListResponseDto editTaskList(Long taskListById, TaskListRequestDto taskListRequestDto) {
        //check task list is exist
        TaskList taskList = getTaskList(taskListById);
        //check task list name is duplicate
        taskListNameIsExistForEdit(taskListRequestDto.getTaskListName(),taskList.getTaskListId(),taskList.getUser().getId());
        //check user is exist
        userService.getUser(taskListRequestDto.getUserId());
        taskList.setListName(taskListRequestDto.getTaskListName());

        taskListRepository.save(taskList);
        return Mapper.taskListToTaskListResponseDto(taskList);
    }

    @Override
    public void taskListNameIsExistForEdit(String taskListName, Long taskListId, Long userId) {
        Optional<TaskList> taskList = taskListRepository.listNameIsExistForEdit(taskListName,taskListId,userId);
        if(taskList.isPresent()){
            throw new TaskListNameIsExisException(HttpStatus.CONFLICT.value(), " This task list name is exist - please enter a new one");
        }
    }

}
