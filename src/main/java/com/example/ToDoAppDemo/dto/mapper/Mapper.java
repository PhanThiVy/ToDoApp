package com.example.ToDoAppDemo.dto.mapper;

import com.example.ToDoAppDemo.dto.responseDto.RoleResponseDto;
import com.example.ToDoAppDemo.dto.responseDto.TaskListResponseDto;
import com.example.ToDoAppDemo.dto.responseDto.UserResponseDto;
import com.example.ToDoAppDemo.model.Role;
import com.example.ToDoAppDemo.model.Task;
import com.example.ToDoAppDemo.model.TaskList;
import com.example.ToDoAppDemo.model.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static RoleResponseDto roleToRoleResponseDto(Role role) {
        RoleResponseDto roleResponseDto = new RoleResponseDto();

        roleResponseDto.setId(role.getId());
        roleResponseDto.setRoleName(role.getRoleName());

        List<String> userNames = new ArrayList<>();
        List<User> users = role.getUsers();
        for (User user : users) {
            userNames.add(user.getUserName());
        }
        roleResponseDto.setUserNames(userNames);

        return roleResponseDto;

    }
    //map user to UserResponseDto

    public static UserResponseDto userToUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId(user.getId());
        userResponseDto.setFullName(user.getFullName());
        userResponseDto.setUserName(user.getUserName());
        userResponseDto.setPassword(user.getPassword());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setRegistrationDate(user.getRegistrationDate());
        userResponseDto.setLocked(user.getLocked());
        //add role
        List<String> roleNames = new ArrayList<>();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            roleNames.add(role.getRoleName());
        }
        userResponseDto.setRoleNames(roleNames);
        //add task list name
        List<String> taskListName = new ArrayList<>();
        List<TaskList> taskLists = user.getTaskList();
        for (TaskList taskList : taskLists) {
            taskListName.add(taskList.getListName());
        }
        userResponseDto.setTaskListName(taskListName);

        return userResponseDto;

    }
    //map form TaskList to TaskListResponseDto
    public static TaskListResponseDto TaskListToTaskListResponseDto(TaskList taskList) {
        TaskListResponseDto taskListResponseDto = new TaskListResponseDto();

        taskListResponseDto.setTaskListId(taskList.getTaskListId());
        taskListResponseDto.setTaskListName(taskList.getListName());
        //add user
        taskListResponseDto.setUserName(taskList.getUser().getUserName());
        //add task
        List<String> taskListName = new ArrayList<>();
        for (Task task: taskList.getTasks()) {
            taskListName.add(task.getTaskName());
        }
        taskListResponseDto.setTaskName(taskListName);

        return taskListResponseDto;
    }
}
