package com.example.ToDoAppDemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    @Column(nullable = false, length = 20)
    private String taskName;
    @Column(nullable = false, length = 500)
    private String description;
    @DateTimeFormat(pattern = "dd/MM/YYYY")
    @FutureOrPresent
    private LocalDate startDate;
    @DateTimeFormat(pattern = "dd/MM/YYYY")
    @FutureOrPresent
    private LocalDate endDate;
    @DateTimeFormat(pattern = "dd/MM/YYYY")
    private LocalDate updateDate;
    @Column(nullable = false)
    private Boolean isCompleted=false;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_list_id", nullable = false, referencedColumnName = "taskListId")
    private TaskList taskList;
}
