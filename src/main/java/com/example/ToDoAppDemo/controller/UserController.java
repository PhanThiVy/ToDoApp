package com.example.ToDoAppDemo.controller;

import com.example.ToDoAppDemo.dto.requestDto.LoginRequestDto;

import com.example.ToDoAppDemo.dto.requestDto.UserRequestDto;
import com.example.ToDoAppDemo.dto.responseDto.UserResponseDto;
import com.example.ToDoAppDemo.exception.userException.UserNameExistException;
import com.example.ToDoAppDemo.exception.userException.UserNotValidException;
import com.example.ToDoAppDemo.jwt.CustomUserDetails;
import com.example.ToDoAppDemo.jwt.JwtTokenProvider;
import com.example.ToDoAppDemo.service.iService.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;

    @Transactional(rollbackOn = {UserNotValidException.class, UserNameExistException.class})
    @PostMapping("/signUp")
    public ResponseEntity<UserResponseDto> addUser(@Valid @RequestBody UserRequestDto userRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            bindingResult.getFieldErrors().forEach(
                    error -> errors.put(error.getField(), error.getDefaultMessage())
            );

            String errorMsg = "";

            for (String key : errors.keySet()) {
                errorMsg += "Error by: " + key + ", reason: " + errors.get(key) + " - ";
            }
            throw new UserNotValidException(HttpStatus.BAD_REQUEST.value(), errorMsg);
        }
        UserResponseDto userResponseDto = userService.signUp(userRequestDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginRequestDto loginRequestDto) {

        // Xác thực từ username và password.
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.getUserName(),
                            loginRequestDto.getPassword()
                    )
            );
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>("UserName or Password is not valid", HttpStatus.BAD_REQUEST);
        }


        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        customUserDetails.getUser();
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }


}
