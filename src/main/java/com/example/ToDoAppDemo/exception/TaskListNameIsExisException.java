package com.example.ToDoAppDemo.exception;

import com.example.ToDoAppDemo.exception.ErrorDetail;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TaskListNameIsExisException extends RuntimeException{
    private final ErrorDetail errorDetail;

    public TaskListNameIsExisException(int errorCode, String message) {
        super();
        this.errorDetail = new ErrorDetail().builder()
                .errorCode(errorCode)
                .message(message)
                .build();
    }
}
