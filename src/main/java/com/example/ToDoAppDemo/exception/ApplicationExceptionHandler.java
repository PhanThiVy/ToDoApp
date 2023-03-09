package com.example.ToDoAppDemo.exception;

import com.example.ToDoAppDemo.exception.userException.UserNameExistException;
import com.example.ToDoAppDemo.exception.userException.UserNotFoundException;
import com.example.ToDoAppDemo.exception.userException.UserNotValidException;
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
    @ExceptionHandler(UserNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetail handlerUserNotValidException(UserNotValidException ex){
        return ex.getErrorDetail();
    }
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetail handlerNotFoundException(UserNotFoundException ex){
        return ex.getErrorDetail();
    }
}
