package com.example.ToDoAppDemo.dto.requestDto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LoginRequestDto {
    @NotEmpty
    private String userName;

    @NotEmpty
    private String password;
}
