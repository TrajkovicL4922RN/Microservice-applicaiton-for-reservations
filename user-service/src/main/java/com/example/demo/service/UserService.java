package com.example.demo.service;

import com.example.demo.controller.dto.CreateManagerDto;
import com.example.demo.controller.dto.CreateUserDto;
import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.dto.UserUpdateDto;
import jakarta.validation.Valid;
import org.springframework.data.relational.core.sql.In;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto registerUser(CreateUserDto createUserDto);
    UserDto registerManager(CreateManagerDto createManagerDto);
    void banUser(Long userId);
    UserDto update(Long id, @Valid UserUpdateDto userUpdateDto);
    UserDto updateBrojRezervacija(String username);
    UserDto decBrojRezervacija(String username);


   //boolean validatePassword(String password, String password1);
}
