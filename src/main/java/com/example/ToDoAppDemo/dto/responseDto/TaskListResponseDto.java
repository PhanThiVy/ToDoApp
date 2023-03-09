package com.example.ToDoAppDemo.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TaskListResponseDto {
    private Long taskListId;
    private String taskListName;
    private List<String> taskName;
    private String userName;
}
