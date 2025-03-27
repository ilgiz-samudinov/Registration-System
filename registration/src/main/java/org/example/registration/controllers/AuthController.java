package org.example.registration.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.registration.dto.JwtResponse;
import org.example.registration.dto.JwtRequest;
import org.example.registration.entities.User;
import org.example.registration.jwt.JwtService;
import org.example.registration.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import java.util.List;

@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @GetMapping()
    public ResponseEntity<List<User>>  users(){
        return ResponseEntity
                .ok(userService.getAllUsers());
    }



    @PostMapping("/register")
    public ResponseEntity<JwtRequest> register(@Valid @RequestBody JwtRequest jwtRequest){
        JwtRequest savedUser = userService.registerUser(jwtRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getName(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwt = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken, userDetails.getUsername()));
    }

    @PostMapping("/admin")
    public ResponseEntity<JwtRequest> adminSave(@Valid @RequestBody JwtRequest jwtRequest){
        JwtRequest savedUser = userService.registerAdmin(jwtRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }


    @PutMapping("/{id}")
    public ResponseEntity<JwtRequest> update(@PathVariable ("id") Long id,
                                             @RequestBody JwtRequest jwtRequest){
        JwtRequest updatedJwtRequest = userService.update(id, jwtRequest);
        return new ResponseEntity<>(updatedJwtRequest, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable ("id") Long id) {
        userService.delete(id);
        return  ResponseEntity
                .noContent().build();
    }
}
