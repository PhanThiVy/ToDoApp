package com.example.ToDoAppDemo.service;

import com.example.ToDoAppDemo.model.PasswordResetToken;

public interface PasswordResetService {
    public PasswordResetToken generateToken(String email);
    public void resetPassword(String token, String newPassword);
    public void changePassword(String currentPassword, String newPassword);
}
