package com.example.ToDoAppDemo.service.iService;

import com.example.ToDoAppDemo.dto.requestDto.UserRequestDto;
import com.example.ToDoAppDemo.dto.responseDto.UserResponseDto;
import com.example.ToDoAppDemo.model.User;
import org.springframework.stereotype.Service;


public interface UserService {
    public UserResponseDto addUser(UserRequestDto userRequestDto);
    public User getUser(String userId);
    public UserResponseDto getUserById(String userId);
    public UserResponseDto addAdmin(UserRequestDto userRequestDto);
    public UserResponseDto addRoleToUserToUser(String userId, String roleId);
    public UserResponseDto deleteRoleFromUser(String bookId, String roleId);
    public boolean isNumberic(String userId);
    public boolean userNameIsExist(String userName);
}
