package com.example.ToDoAppDemo.service;

import com.example.ToDoAppDemo.dto.mapper.Mapper;
import com.example.ToDoAppDemo.dto.requestDto.TaskListRequestDto;
import com.example.ToDoAppDemo.dto.responseDto.TaskListResponseDto;
import com.example.ToDoAppDemo.exception.TaskListNameIsExisException;
import com.example.ToDoAppDemo.exception.TaskListNotFoundException;
import com.example.ToDoAppDemo.model.TaskList;
import com.example.ToDoAppDemo.model.User;
import com.example.ToDoAppDemo.repository.TaskListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskListServiceImpl implements TaskListService {
    private final TaskListRepository taskListRepository;
    private final UserService userService;

    @Autowired
    private PageRequest taskListPageable;

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

    @Override
    public TaskListResponseDto getTaskListById(Long taskListById) {
        TaskList taskList = getTaskList(taskListById);
        return Mapper.taskListToTaskListResponseDto(taskList);
    }

    @Override
    public Page<TaskListResponseDto> getAllTaskList(int pageNumber, Long userId) {
        //check user
        User user = userService.getUser(userId);

        //get all tasklist by user
        Page<TaskList> taskLists = taskListRepository.findByUser(taskListPageable.withPage(pageNumber),user);

        //map Page<TaskList> to List<TaskListResponseDto>
        List<TaskListResponseDto> taskListResponseDtoList=taskLists.stream().map(taskList -> new TaskListResponseDto(taskList.getTaskListId(),
                taskList.getListName(),taskList.getTasks().stream().map(task -> task.getTaskName()).collect(Collectors.toList()), taskList.getUser().getUserName())).collect(Collectors.toList());

        return new PageImpl<>(taskListResponseDtoList, taskLists.getPageable(),taskListResponseDtoList.size());
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
