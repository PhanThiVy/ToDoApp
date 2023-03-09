package com.example.ToDoAppDemo.controller;

import com.example.ToDoAppDemo.dto.requestDto.LoginRequestDto;
import com.example.ToDoAppDemo.dto.requestDto.UserRequestDto;
import com.example.ToDoAppDemo.dto.responseDto.LoginResponseDto;
import com.example.ToDoAppDemo.dto.responseDto.UserResponseDto;
import com.example.ToDoAppDemo.exception.userException.UserNotValidException;
import com.example.ToDoAppDemo.jwt.CustomUserDetails;
import com.example.ToDoAppDemo.jwt.JwtTokenProvider;
import com.example.ToDoAppDemo.service.iService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUserName(),
                        loginRequestDto.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken(authentication);
//        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
//        LoginResponseDto loginResponseDto = new LoginResponseDto().builder()
//                .userResponseDto(customUserDetails.getUserResponseDto())
//                .token(jwt)
//                .build();
        return new ResponseEntity<>(jwt,HttpStatus.OK);
    }

}
