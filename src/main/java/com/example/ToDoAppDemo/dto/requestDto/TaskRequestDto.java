package com.example.ToDoAppDemo.dto.requestDto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TaskRequestDto {
    @NotEmpty
    @Size(min = 1 ,max = 20)
    private String taskName;
    @Size(min = 0 ,max = 500)
    private String description;
    @DateTimeFormat(pattern = "dd/MM/YYYY")
    @FutureOrPresent
    private LocalDate startDate;
    @DateTimeFormat(pattern = "dd/MM/YYYY")
    @FutureOrPresent
    private LocalDate endDate;


}
