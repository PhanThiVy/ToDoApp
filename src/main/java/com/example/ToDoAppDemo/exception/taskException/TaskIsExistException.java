package com.example.ToDoAppDemo.exception.taskException;

import com.example.ToDoAppDemo.exception.ErrorDetail;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TaskIsExistException extends RuntimeException{
    private final ErrorDetail errorDetail;

    public TaskIsExistException(int errorCode, String message) {
        super();
        this.errorDetail = new ErrorDetail().builder()
                .errorCode(errorCode)
                .message(message)
                .build();
    }
}
