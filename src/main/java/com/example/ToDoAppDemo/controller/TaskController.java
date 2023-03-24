package com.example.ToDoAppDemo.controller;

import com.example.ToDoAppDemo.dto.requestDto.TaskRequestDto;
import com.example.ToDoAppDemo.dto.responseDto.TaskResponseDto;
import com.example.ToDoAppDemo.exception.TaskIsNotValidException;
import com.example.ToDoAppDemo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    @Transactional
    @PostMapping("/add")
    public ResponseEntity<TaskResponseDto> addTask(@Valid @RequestBody TaskRequestDto taskRequestDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            bindingResult.getFieldErrors().forEach(
                    error -> errors.put(error.getField(), error.getDefaultMessage())
            );

            String errorMsg = "";

            for (String key : errors.keySet()) {
                errorMsg += "Error by: " + key + ", reason: " + errors.get(key) + " - ";
            }
            throw new TaskIsNotValidException(HttpStatus.BAD_REQUEST.value(), errorMsg);
        }
        TaskResponseDto taskResponseDto = taskService.addTask(taskRequestDto);
        return new ResponseEntity<>(taskResponseDto, HttpStatus.OK);
    }

    @GetMapping("/getById")
    public ResponseEntity<TaskResponseDto> getTaskById(@RequestParam Long taskId){
        TaskResponseDto taskResponseDto = taskService.getTaskById(taskId);
        return new ResponseEntity<>(taskResponseDto,HttpStatus.OK);
    }

    @PutMapping("/edit/{taskId}")
    public ResponseEntity<TaskResponseDto> editTask(@PathVariable Long taskId, @RequestBody TaskRequestDto taskRequestDto){
        TaskResponseDto taskResponseDto = taskService.editTask(taskId,taskRequestDto);
        return new ResponseEntity<>(taskResponseDto,HttpStatus.OK);
    }

}
