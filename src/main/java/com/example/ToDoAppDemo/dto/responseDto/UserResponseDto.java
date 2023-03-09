package com.example.ToDoAppDemo.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserResponseDto {
    private Long id;
    private String fullName;
    private String userName;
    private String password;
    private String email;
    private List<String> roleNames;
    private LocalDate registrationDate;
    private Boolean locked;
    private Boolean enabled;
    private List<String> taskListName;
}
