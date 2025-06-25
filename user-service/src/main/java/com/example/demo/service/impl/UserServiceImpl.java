package com.example.demo.service.impl;

import com.example.demo.controller.dto.CreateManagerDto;
import com.example.demo.controller.dto.CreateUserDto;
import com.example.demo.domain.User;
import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.dto.UserUpdateDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto registerUser(CreateUserDto createUserDto) {
        return userMapper.toDto(userRepository.save(userMapper.fromDto(createUserDto)));
    }

    @Override
    public UserDto registerManager(CreateManagerDto createManagerDto) {
        return  userMapper.toDto(userRepository.save(userMapper.managerFromDto(createManagerDto)));
    }

    @Override
    public void banUser(Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setActive(false);
        userRepository.save(user);
    }

    @Override
    public UserDto update(Long id, UserUpdateDto userUpdateDto) {
        User user = userRepository.findUserById(id);
        if(!(userUpdateDto.getEmail() == null)){
            user.setEmail(userUpdateDto.getEmail());
        }
        if(!(userUpdateDto.getIme() == null)){
            user.setIme(userUpdateDto.getIme());
        }
        if(!(userUpdateDto.getPrezime() == null)){
            user.setPrezime(userUpdateDto.getPrezime());
        }
        if(!(userUpdateDto.getPassword() == null)){
            user.setPassword(passwordEncoder.encode(userUpdateDto.getPassword()));

        }
        if(!(userUpdateDto.getUsername() == null)){
            user.setUsername(userUpdateDto.getUsername());
        }
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateBrojRezervacija(String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        int br = user.getBrRez();
        br++;
        user.setBrRez(br);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto decBrojRezervacija(String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        int br = user.getBrRez();
        br--;
        user.setBrRez(br);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("usao");
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Korisnik sa imenom " + username + " nije pronadjen"));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName().toString()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("usao");
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Kornisnik sa imenom " + username + " nije pronadjen"));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName().toString()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }*/

    /*public boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }*/


