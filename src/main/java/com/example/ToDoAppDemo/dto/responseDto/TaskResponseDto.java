package com.example.ToDoAppDemo.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TaskResponseDto {
    private long taskId;
    private String taskName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate updateDate;
    private Boolean isCompleted;
    private Long taskListId;
}
