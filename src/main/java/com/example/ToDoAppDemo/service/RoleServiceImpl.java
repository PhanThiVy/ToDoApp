package com.example.ToDoAppDemo.service;

import com.example.ToDoAppDemo.dto.mapper.Mapper;
import com.example.ToDoAppDemo.dto.responseDto.RoleResponseDto;
import com.example.ToDoAppDemo.model.Role;
import com.example.ToDoAppDemo.model.User;
import com.example.ToDoAppDemo.repository.RoleRepository;
import com.example.ToDoAppDemo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final PageRequest rolePageable;
    @Override
    public Page<RoleResponseDto> getRoleList(int pageNumber) {
        Page<Role> rolePage = roleRepository.findAll(rolePageable.withPage(pageNumber));
        return rolePage.map(role -> Mapper.roleToRoleResponseDto(role));
    }

    @Override
    public Role getRoleByName(String roleName) {
        Role role = roleRepository.findRoleByRoleName(roleName);
        return role;
    }

    @Override
    public void addUser(Role role, User user) {
        role.addUser(user);
        roleRepository.save(role);
    }
}
