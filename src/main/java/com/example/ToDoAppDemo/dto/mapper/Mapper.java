package com.example.ToDoAppDemo.dto.mapper;

import com.example.ToDoAppDemo.dto.responseDto.RoleResponseDto;
import com.example.ToDoAppDemo.model.Role;
import com.example.ToDoAppDemo.model.User;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static RoleResponseDto roleToRoleResponseDto(Role role) {
        RoleResponseDto roleResponseDto = new RoleResponseDto();

        roleResponseDto.setId(role.getId());
        roleResponseDto.setRoleName(role.getRoleName());

        List<String> userNames = new ArrayList<>();
        List<User> users = role.getUsers();
        for (User user : users) {
            userNames.add(user.getUserName());
        }
        roleResponseDto.setUserNames(userNames);

        return roleResponseDto;

    }
}
