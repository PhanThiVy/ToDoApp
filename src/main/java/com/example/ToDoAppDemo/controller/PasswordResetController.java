package com.example.ToDoAppDemo.controller;

import com.example.ToDoAppDemo.model.PasswordResetToken;
import com.example.ToDoAppDemo.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passwordReset")
@RequiredArgsConstructor
public class PasswordResetController {
    private final PasswordResetService passwordResetService;

    @PostMapping("/token")
    public ResponseEntity<String> generateToken(@RequestParam("email") String email) {
        PasswordResetToken token = passwordResetService.generateToken(email);
        return ResponseEntity.ok(token.getToken());
    }

    @PostMapping("/reset")
    public ResponseEntity<Void> resetPassword(@RequestParam("token") String token,
                                              @RequestParam("password") String newPassword) {
        passwordResetService.resetPassword(token, newPassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Void> changePassword(@RequestParam("currentPassword") String currentPassword,
                                               @RequestParam("newPassword") String newPassword) {

        passwordResetService.changePassword(currentPassword,newPassword);
        // Return a success response
        return new ResponseEntity<>(HttpStatus.OK);


    }
}
