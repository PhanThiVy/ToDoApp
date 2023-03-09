package com.example.ToDoAppDemo.dto.requestDto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserRequestDto {
    @NotEmpty
    @Size(min = 5,max = 60)
    private String fullName;
    @NotEmpty
    @Size(min = 5,max = 60)
    private String userName;

    @NotEmpty
    @Size(min = 3,max = 30)
    private String password;

    @NotEmpty
    @Email
    private String email;
}
