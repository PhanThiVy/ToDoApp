package com.example.ToDoAppDemo.controller;

import com.example.ToDoAppDemo.dto.requestDto.TaskListRequestDto;
import com.example.ToDoAppDemo.dto.responseDto.TaskListResponseDto;
import com.example.ToDoAppDemo.exception.taskListException.TaskListNotValidException;
import com.example.ToDoAppDemo.service.iService.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/taskList")
@RequiredArgsConstructor
public class TaskListController {
    private final TaskListService taskListService;
    @PostMapping("/add/{userId}")
    public ResponseEntity<TaskListResponseDto> addTaskList(@PathVariable String userId,@Valid @RequestBody TaskListRequestDto taskListRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            bindingResult.getFieldErrors().forEach(
                    error -> errors.put(error.getField(), error.getDefaultMessage())
            );

            String errorMsg = "";

            for (String key : errors.keySet()) {
                errorMsg += "Error by: " + key + ", reason: " + errors.get(key) + " - ";
            }
            throw new TaskListNotValidException(HttpStatus.BAD_REQUEST.value(), errorMsg);
        }
        TaskListResponseDto taskListResponseDto = taskListService.addTaskList(userId,taskListRequestDto);
        return new ResponseEntity<>(taskListResponseDto, HttpStatus.OK);
    }

    @GetMapping("/getById")
    public ResponseEntity<TaskListResponseDto> getTaskListById(@RequestParam String taskListId){
        TaskListResponseDto taskListResponseDto = taskListService.getTaskListById(taskListId);
        return new ResponseEntity<>(taskListResponseDto, HttpStatus.OK);
    }

    @PutMapping("/editTaskList/{taskListId}")
    public ResponseEntity<TaskListResponseDto> edit(@PathVariable String taskListId, @RequestBody TaskListRequestDto taskListRequestDto) {
        TaskListResponseDto taskListResponseDto = taskListService.editTaskList(taskListId, taskListRequestDto);
        return new ResponseEntity<>(taskListResponseDto, HttpStatus.OK);
    }
}
