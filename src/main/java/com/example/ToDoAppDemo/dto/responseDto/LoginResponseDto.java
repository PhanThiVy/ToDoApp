package com.example.ToDoAppDemo.dto.responseDto;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetailsService;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginResponseDto {
    private UserResponseDto userResponseDto;
    private String token;
}
