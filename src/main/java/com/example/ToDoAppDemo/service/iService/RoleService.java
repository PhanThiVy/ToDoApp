package com.example.ToDoAppDemo.service.iService;

import com.example.ToDoAppDemo.dto.responseDto.RoleResponseDto;
import com.example.ToDoAppDemo.model.Role;
import com.example.ToDoAppDemo.model.User;
import org.springframework.data.domain.Page;

public interface RoleService {
    public Page<RoleResponseDto> getRoleList(int pageNumber);
    public Role getRoleByName(String roleName);
    void addUser(Role role, User user);
}
