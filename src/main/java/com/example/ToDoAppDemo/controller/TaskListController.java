package com.example.ToDoAppDemo.controller;

import com.example.ToDoAppDemo.dto.requestDto.TaskListRequestDto;
import com.example.ToDoAppDemo.dto.responseDto.TaskListResponseDto;
import com.example.ToDoAppDemo.exception.TaskListNotValidException;
import com.example.ToDoAppDemo.service.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    @PostMapping("/add")
    public ResponseEntity<TaskListResponseDto> addTaskList(@Valid @RequestBody TaskListRequestDto taskListRequestDto, BindingResult bindingResult) {
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
        TaskListResponseDto taskListResponseDto = taskListService.addTaskList(taskListRequestDto);
        return new ResponseEntity<>(taskListResponseDto, HttpStatus.OK);
    }

    @GetMapping("/getById")
    public ResponseEntity<TaskListResponseDto> getTaskListById(@RequestParam Long taskListId){
        TaskListResponseDto taskListResponseDto = taskListService.getTaskListById(taskListId);
        return new ResponseEntity<>(taskListResponseDto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<TaskListResponseDto>> getAll(@RequestParam(defaultValue = "0") int pageNumber,@RequestParam Long userId){
        Page<TaskListResponseDto> taskListResponseDtos = taskListService.getAllTaskList(pageNumber,userId);
        return new ResponseEntity<>(taskListResponseDtos, HttpStatus.OK);
    }

    @PutMapping("/editTaskList/{taskListId}")
    public ResponseEntity<TaskListResponseDto> edit(@PathVariable Long taskListId, @RequestBody TaskListRequestDto taskListRequestDto) {
        TaskListResponseDto taskListResponseDto = taskListService.editTaskList(taskListId, taskListRequestDto);
        return new ResponseEntity<>(taskListResponseDto, HttpStatus.OK);
    }
}
