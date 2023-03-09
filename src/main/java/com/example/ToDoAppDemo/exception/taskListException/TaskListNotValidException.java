package com.example.ToDoAppDemo.exception.taskListException;

import com.example.ToDoAppDemo.exception.ErrorDetail;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TaskListNotValidException extends RuntimeException{
    private final ErrorDetail errorDetail;

    public TaskListNotValidException(int errorCode, String message) {
        super();
        this.errorDetail = new ErrorDetail().builder()
                .errorCode(errorCode)
                .message(message)
                .build();
    }
}
