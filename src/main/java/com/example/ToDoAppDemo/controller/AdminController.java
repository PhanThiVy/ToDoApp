package com.example.ToDoAppDemo.controller;

import com.example.ToDoAppDemo.dto.responseDto.UserResponseDto;
import com.example.ToDoAppDemo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    @GetMapping("/getUserById")
    public ResponseEntity<UserResponseDto> getUserById(@RequestParam Long userId){
        UserResponseDto userResponseDto = adminService.getUserById(userId);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PutMapping("/blockUser")
    public ResponseEntity<UserResponseDto> blockUser(@RequestParam Long userId){
        UserResponseDto userResponseDto = adminService.blockUser(userId);
        return new ResponseEntity<>(userResponseDto,HttpStatus.OK
        );
    }

}
