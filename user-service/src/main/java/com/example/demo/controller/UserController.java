package com.example.demo.controller;


import com.example.demo.controller.dto.CreateManagerDto;
import com.example.demo.controller.dto.CreateUserDto;
import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.dto.UserUpdateDto;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody CreateUserDto createUserDto) {
        return new ResponseEntity<>(userService.registerUser(createUserDto), HttpStatus.CREATED);
    }
    @PostMapping("/register-manager")
    public ResponseEntity<?> registerManager(@RequestBody CreateManagerDto createManagerDto) {
        return new ResponseEntity<>(userService.registerManager(createManagerDto), HttpStatus.CREATED);
    }
    @PatchMapping("ban-user/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> banUser(@PathVariable Long id) {
        userService.banUser(id);
        return ResponseEntity.ok("User banned successfully");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<UserDto> update(@PathVariable("id") Long id
            , @RequestBody @Valid UserUpdateDto userUpdateDto) {

        return new ResponseEntity<>(userService.update(id, userUpdateDto), HttpStatus.OK);
    }

    @PutMapping("/update-reservations")
    public ResponseEntity<String> incrementReservations(@RequestParam String username) {
        userService.updateBrojRezervacija(username);
        return new ResponseEntity<>("Reservation count incremented successfully", HttpStatus.OK);
    }

    @PutMapping("/smanji-reservations")
    public ResponseEntity<String> decrementReservations(@RequestParam String username) {
        userService.decBrojRezervacija(username);
        return new ResponseEntity<>("Reservation count decrement successfully", HttpStatus.OK);
    }


}
