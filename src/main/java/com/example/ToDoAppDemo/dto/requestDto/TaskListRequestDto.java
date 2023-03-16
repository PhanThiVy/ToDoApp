package com.example.ToDoAppDemo.dto.requestDto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TaskListRequestDto {
    @NotEmpty
    @Size(min = 1,max = 20)
    private String taskListName;
    private Long userId;
}
