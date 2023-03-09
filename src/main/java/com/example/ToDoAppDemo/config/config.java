package com.example.ToDoAppDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Configuration
public class Config {
    @Bean
    public PageRequest rolePageable() {
        return PageRequest.of(0, 20, Sort.by(Sort.Direction.ASC, "roleName"));
    }

    @Bean
    public PageRequest userPageable() {
        return PageRequest.of(0, 20, Sort.by(Sort.Direction.ASC, "username"));
    }
}
