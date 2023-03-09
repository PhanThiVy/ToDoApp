package com.example.ToDoAppDemo.service.serviceDetails;

import com.example.ToDoAppDemo.dto.mapper.Mapper;
import com.example.ToDoAppDemo.dto.requestDto.UserRequestDto;
import com.example.ToDoAppDemo.dto.responseDto.UserResponseDto;
import com.example.ToDoAppDemo.exception.userException.UserNameExistException;
import com.example.ToDoAppDemo.exception.userException.UserNotFoundException;
import com.example.ToDoAppDemo.model.User;
import com.example.ToDoAppDemo.repository.UserRepository;
import com.example.ToDoAppDemo.service.iService.RoleService;
import com.example.ToDoAppDemo.service.iService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String USER = "USER";
    private static final String ADMIN = "ADMIN";
    private final UserRepository userRepository;
    private final RoleService roleService;
    @Override
    public UserResponseDto signUp(UserRequestDto userRequestDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        //set full name
        user.setFullName(userRequestDto.getFullName());
        //check and set user name
        if (!userNameIsExist(userRequestDto.getUserName())) {
            user.setUserName(userRequestDto.getUserName());
        }

        //set password
        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());
        user.setPassword(encodedPassword);
        user.addRole(roleService.getRoleByName(UserServiceImpl.USER));
        //set mail
        user.setEmail(userRequestDto.getEmail());

        //save user
        userRepository.save(user);
        //save role
        roleService.addUser(roleService.getRoleByName(UserServiceImpl.USER),user);
        return Mapper.userToUserResponseDto(user);
    }

    @Override
    public User getUser(String userId) {
        if (userId.matches("\\d+")) {
            Long id = Long.parseLong(userId);
            //check role is exist
            User user = userRepository.findById(id).orElseThrow(()
                    -> new UserNotFoundException(HttpStatus.NOT_FOUND.value(),"Can not find user with id "+ userId));
            return user;
        }
        //if roleId is not number , thrown NotFoundException
        throw new UserNotFoundException(HttpStatus.NOT_FOUND.value(),"Please enter number for user id.");
    }

    @Override
    public UserResponseDto getUserById(String userId) {
        User user = getUser(userId);
        return Mapper.userToUserResponseDto(user);
    }


    @Override
    public UserResponseDto addRoleToUserToUser(String userId, String roleId) {
        return null;
    }

    @Override
    public UserResponseDto deleteRoleFromUser(String bookId, String roleId) {
        return null;
    }


    @Override
    public boolean userNameIsExist(String userName) {
        User user = userRepository.findUserByUserName(userName);
        if (user != null) {
            throw new UserNameExistException(HttpStatus.CONFLICT.value(), " This user name is exist - please enter a new one");
        }
        return false;
    }
}
