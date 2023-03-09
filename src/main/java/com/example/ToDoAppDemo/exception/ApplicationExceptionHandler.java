package com.example.ToDoAppDemo.exception;

import com.example.ToDoAppDemo.exception.userException.UserNameExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(UserNameExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDetail handlerUserNameExistException(UserNameExistException ex){
        return ex.getErrorDetail();
    }
}
