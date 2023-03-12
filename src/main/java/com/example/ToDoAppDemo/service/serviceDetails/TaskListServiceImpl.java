package com.example.ToDoAppDemo.service.serviceDetails;

import com.example.ToDoAppDemo.dto.mapper.Mapper;
import com.example.ToDoAppDemo.dto.requestDto.TaskListRequestDto;
import com.example.ToDoAppDemo.dto.responseDto.TaskListResponseDto;
import com.example.ToDoAppDemo.exception.taskListException.TaskListNameIsExisException;
import com.example.ToDoAppDemo.exception.taskListException.TaskListNotFoundException;
import com.example.ToDoAppDemo.exception.userException.UserNotFoundException;
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

    @Override
    public TaskListResponseDto addTaskList(String userId,TaskListRequestDto taskListRequestDto) {
        TaskList taskList = new TaskList();
        //check user is exist
        User user = userService.getUser(userId);
        //check task list name is exist
        taskListNameIsExistForAdd(taskListRequestDto.getTaskListName(), Long.valueOf(userId));

        taskList.setListName(taskListRequestDto.getTaskListName());
        taskList.setUser(user);
        //save task list
        taskListRepository.save(taskList);
        return Mapper.TaskListToTaskListResponseDto(taskList);
    }

    @Override
    public void taskListNameIsExistForAdd(String taskListName, Long userId) {
        Optional<TaskList> taskList = taskListRepository.listNameIsExistForAdd(taskListName, userId);
        if (taskList.isPresent()) {
            throw new TaskListNameIsExisException(HttpStatus.CONFLICT.value(), " This task list name is exist - please enter a new one");
        }
    }

    @Override
    public TaskList getTaskList(String taskListById) {
        if (taskListById.matches("\\d+")) {
            Long id = Long.parseLong(taskListById);
            //check role is exist
            Optional<TaskList> taskList = taskListRepository.findById(id);
            if(taskList.isEmpty()){
                throw new TaskListNotFoundException(HttpStatus.NOT_FOUND.value(), "Can not find task list with id " + taskListById);
            }
            return taskList.get();
        }
        //if roleId is not number , thrown NotFoundException
        throw new TaskListNotFoundException(HttpStatus.NOT_FOUND.value(), "Please enter number for task list id.");
    }

    @Override
    public TaskListResponseDto getTaskListById(String taskListById) {
        TaskList taskList = getTaskList(taskListById);
        return Mapper.TaskListToTaskListResponseDto(taskList);
    }

    @Override
    public TaskListResponseDto editTaskList(String taskListById, TaskListRequestDto taskListRequestDto) {
        //check task list is exist
        TaskList taskList = getTaskList(taskListById);

        //check task list name is duplicate
        taskListNameIsExistForEdit(taskListRequestDto.getTaskListName(),taskList.getTaskListId(),taskList.getUser().getId());

        taskList.setListName(taskListRequestDto.getTaskListName());

        taskListRepository.save(taskList);
        return Mapper.TaskListToTaskListResponseDto(taskList);
    }

    @Override
    public void taskListNameIsExistForEdit(String taskListName, Long taskListId, Long userId) {
        Optional<TaskList> taskList = taskListRepository.listNameIsExistForEdit(taskListName,taskListId,userId);
        if(taskList.isPresent()){
            throw new TaskListNameIsExisException(HttpStatus.CONFLICT.value(), " This task list name is exist - please enter a new one");
        }
    }

}
