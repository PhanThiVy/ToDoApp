package com.example.ToDoAppDemo.service.iService;

import com.example.ToDoAppDemo.dto.responseDto.UserResponseDto;

public interface AdminService {
    public UserResponseDto blockUser(Long userId);
    public UserResponseDto getUserById(Long userId);

}
