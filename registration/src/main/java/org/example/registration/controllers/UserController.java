package org.example.registration.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.registration.dto.UserDto;
import org.example.registration.entities.User;
import org.example.registration.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<User>>  users(){
        return ResponseEntity
                .ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserDto> save(@Valid @RequestBody UserDto userDto){
        UserDto savedUser = userService.save(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable ("id") Long id,
                                       @RequestBody UserDto userDto){

        UserDto updatedUserDto = userService.update(id,userDto);
        return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable ("id") Long id) {
        userService.delete(id);
        return  ResponseEntity
                .noContent().build();
    }
}
