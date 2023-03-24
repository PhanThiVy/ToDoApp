package com.example.ToDoAppDemo.service;

import com.example.ToDoAppDemo.dto.requestDto.UserRequestDto;
import com.example.ToDoAppDemo.dto.responseDto.UserResponseDto;
import com.example.ToDoAppDemo.model.TaskList;
import com.example.ToDoAppDemo.model.User;


public interface UserService {
    public UserResponseDto signUp(UserRequestDto userRequestDto);
    public User getUser(Long userId);
    public UserResponseDto addRoleToUserToUser(Long userId, Long roleId);
    public UserResponseDto deleteRoleFromUser(Long bookId, Long roleId);
    public boolean userNameIsExist(String userName);
}
