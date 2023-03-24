package com.example.ToDoAppDemo.controller;

import com.example.ToDoAppDemo.dto.responseDto.RoleResponseDto;
import com.example.ToDoAppDemo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    //get role list
    @GetMapping("/list")
    public ResponseEntity<Page<RoleResponseDto>> getRoleList(@RequestParam(defaultValue = "0") int pageNumber){
        Page<RoleResponseDto> roleResponseDtos = roleService.getRoleList(pageNumber);
        return new ResponseEntity<>(roleResponseDtos, HttpStatus.OK);
    }

}
