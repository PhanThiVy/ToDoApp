package com.example.ToDoAppDemo.exception;

import com.example.ToDoAppDemo.exception.ErrorDetail;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ResetPasswordTokenNotValidException extends RuntimeException{
    private final ErrorDetail errorDetail;

    public ResetPasswordTokenNotValidException(int errorCode, String message) {
        super();
        this.errorDetail = new ErrorDetail().builder()
                .errorCode(errorCode)
                .message(message)
                .build();
    }
}
