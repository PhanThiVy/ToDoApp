package com.example.ToDoAppDemo.service.serviceDetails;

import com.example.ToDoAppDemo.dto.mapper.Mapper;
import com.example.ToDoAppDemo.dto.responseDto.UserResponseDto;
import com.example.ToDoAppDemo.model.User;
import com.example.ToDoAppDemo.repository.UserRepository;
import com.example.ToDoAppDemo.service.iService.AdminService;
import com.example.ToDoAppDemo.service.iService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserService userService;
    private final UserRepository userRepository;
    @Override
    public UserResponseDto getUserById(Long userId) {
        User user = userService.getUser(userId);
        return Mapper.userToUserResponseDto(user);
    }

    @Override
    public UserResponseDto blockUser(Long userId) {
        User user =userService.getUser(userId);
        user.setLocked(true);
        userRepository.save(user);
        return Mapper.userToUserResponseDto(user);

    }
}
