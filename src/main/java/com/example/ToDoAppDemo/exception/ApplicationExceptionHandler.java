package com.example.ToDoAppDemo.exception;

import com.example.ToDoAppDemo.exception.passwordException.CurrentPasswordNotMatch;
import com.example.ToDoAppDemo.exception.taskException.TaskIsExistException;
import com.example.ToDoAppDemo.exception.taskException.TaskIsNotValidException;
import com.example.ToDoAppDemo.exception.taskException.TaskNotFoundException;
import com.example.ToDoAppDemo.exception.taskListException.TaskListNameIsExisException;
import com.example.ToDoAppDemo.exception.taskListException.TaskListNotFoundException;
import com.example.ToDoAppDemo.exception.taskListException.TaskListNotValidException;
import com.example.ToDoAppDemo.exception.userException.UserNameExistException;
import com.example.ToDoAppDemo.exception.userException.UserNotFoundException;
import com.example.ToDoAppDemo.exception.userException.UserNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler({UserNameExistException.class})
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

    @ExceptionHandler(TaskListNameIsExisException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDetail handlerTaskListNameIsExisException(TaskListNameIsExisException ex){
        return ex.getErrorDetail();
    }

    @ExceptionHandler(TaskListNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetail handlerTaskListNotValidException(TaskListNotValidException ex){
        return ex.getErrorDetail();
    }

    @ExceptionHandler(TaskListNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetail handlerTaskListNotFoundException(TaskListNotFoundException ex){
        return ex.getErrorDetail();
    }

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetail handlerTaskNotFoundException(TaskNotFoundException ex){
        return ex.getErrorDetail();
    }

    @ExceptionHandler(TaskIsExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDetail handlerTaskIsExistException(TaskIsExistException ex){
        return ex.getErrorDetail();
    }
    @ExceptionHandler(TaskIsNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetail handlerTaskIsNotValidException(TaskIsNotValidException ex){
        return ex.getErrorDetail();
    }

    @ExceptionHandler(CurrentPasswordNotMatch.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetail handlerCurrentPasswordNotMatch(CurrentPasswordNotMatch ex){
        return ex.getErrorDetail();
    }
}
