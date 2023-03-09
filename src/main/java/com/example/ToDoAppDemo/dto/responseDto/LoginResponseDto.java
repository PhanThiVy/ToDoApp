package com.example.ToDoAppDemo.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetailsService;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class LoginResponseDto {
    private UserResponseDto userResponseDto;
    private String token;
}
