package com.example.ToDoAppDemo.service.iService;

import com.example.ToDoAppDemo.dto.responseDto.RoleResponseDto;
import org.springframework.data.domain.Page;

public interface RoleService {
    public Page<RoleResponseDto> getRoleList(int pageNumber);
}
