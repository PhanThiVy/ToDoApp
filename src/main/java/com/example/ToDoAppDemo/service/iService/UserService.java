package com.example.ToDoAppDemo.service.iService;

import com.example.ToDoAppDemo.dto.requestDto.UserRequestDto;
import com.example.ToDoAppDemo.dto.responseDto.UserResponseDto;
import com.example.ToDoAppDemo.model.TaskList;
import com.example.ToDoAppDemo.model.User;


public interface UserService {
    public UserResponseDto signUp(UserRequestDto userRequestDto);
    public User getUser(String userId);
    public UserResponseDto getUserById(String userId);
    public UserResponseDto addRoleToUserToUser(String userId, String roleId);
    public UserResponseDto deleteRoleFromUser(String bookId, String roleId);
    public boolean userNameIsExist(String userName);
    void addTaskList(User user, TaskList taskList);
    void removeTaskList(User user, TaskList taskList);
}
