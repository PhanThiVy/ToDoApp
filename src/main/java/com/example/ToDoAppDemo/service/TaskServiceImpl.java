package com.example.ToDoAppDemo.service;

import com.example.ToDoAppDemo.dto.mapper.Mapper;
import com.example.ToDoAppDemo.dto.requestDto.TaskRequestDto;
import com.example.ToDoAppDemo.dto.responseDto.TaskResponseDto;
import com.example.ToDoAppDemo.exception.taskException.TaskIsExistException;
import com.example.ToDoAppDemo.exception.taskException.TaskNotFoundException;
import com.example.ToDoAppDemo.model.Task;
import com.example.ToDoAppDemo.model.TaskList;
import com.example.ToDoAppDemo.repository.TasKRepository;
import com.example.ToDoAppDemo.service.TaskListService;
import com.example.ToDoAppDemo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TasKRepository taskRepository;
    private final TaskListService taskListService;
    @Override
    public TaskResponseDto addTask(TaskRequestDto taskRequestDto) {
        Task task = new Task();
        //check task list is exist
        taskListService.getTaskList(taskRequestDto.getTaskListId());
        //check task name is exist
        taskNameIsExistForAdd(taskRequestDto.getTaskName(),taskRequestDto.getTaskListId());
        //set task name
        task.setTaskName(taskRequestDto.getTaskName());
        //set description
        task.setDescription(taskRequestDto.getDescription());
        //set start date
        task.setStartDate(taskRequestDto.getStartDate());
        //set end date
        task.setEndDate(taskRequestDto.getEndDate());
        //set task list id
        TaskList taskList = taskListService.getTaskList(taskRequestDto.getTaskListId());
        task.setTaskList(taskList);

        //save task
        taskRepository.save(task);

        return Mapper.taskToTaskResponseDto(task);
    }

    @Override
    public void taskNameIsExistForAdd(String taskName, Long taskListId) {
        Optional<Task> task = taskRepository.taskNameIsExistForAdd(taskName,taskListId);
        if (task.isPresent()){
            throw new TaskIsExistException(HttpStatus.NOT_FOUND.value(),"Task name is exist - please enter a new one.");
        }
    }

    @Override
    public Task getTask(Long taskId) {
            Optional<Task> task = taskRepository.findById(taskId);
            if (task.isEmpty()){
                throw new TaskNotFoundException(HttpStatus.NOT_FOUND.value(), "Can not find task with id " + taskId);
            }
            return task.get();
        }

    @Override
    public TaskResponseDto getTaskById(Long taskId) {
        Task task = getTask(taskId);
        return Mapper.taskToTaskResponseDto(task);
    }

    @Override
    public TaskResponseDto editTask(Long taskId, TaskRequestDto taskRequestDto) {
        //check task is exist
        Task task = getTask(taskId);
        //check task list is exist
        taskListService.getTaskList(taskRequestDto.getTaskListId());
        //check task name is exist for edit
        taskNameIsExistForEdit(taskRequestDto.getTaskName(),taskId,taskRequestDto.getTaskListId());
        //set task name
        task.setTaskName(taskRequestDto.getTaskName());
        //set description
        task.setDescription(taskRequestDto.getDescription());
        //set start date
        task.setStartDate(taskRequestDto.getStartDate());
        //set end date
        task.setEndDate(taskRequestDto.getEndDate());
        //set update date
        task.setUpdateDate(LocalDate.now());

        taskRepository.save(task);
        return Mapper.taskToTaskResponseDto(task);

    }

    @Override
    public void taskNameIsExistForEdit(String taskName, Long taskId, Long taskListId) {
        Optional<Task> task = taskRepository.taskNameIsExistForEdit(taskName,taskId,taskListId);
        if(task.isPresent()){
            throw new TaskIsExistException(HttpStatus.CONFLICT.value(),"Task name is exist - please enter a new one.");
        }
    }

}
