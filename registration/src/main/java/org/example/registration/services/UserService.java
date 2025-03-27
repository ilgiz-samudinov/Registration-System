package org.example.registration.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.registration.dto.JwtRequest;
import org.example.registration.entities.User;
import org.example.registration.enums.Role;
import org.example.registration.exceptions.ApiAlreadyExistsException;
import org.example.registration.exceptions.ApiNotFoundException;
import org.example.registration.mappers.UserMapper;
import org.example.registration.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public JwtRequest registerUser(JwtRequest jwtRequest) {
        userRepository.findByUserName(jwtRequest.getName())
                .ifPresent(u -> {
                    throw new ApiAlreadyExistsException("Пользователь с таким именем уже существует!");
                });

        userRepository.findByEmail(jwtRequest.getEmail())
                .ifPresent(u -> {
                    throw new ApiAlreadyExistsException("Пользователь с таким email уже существует!");
                });

        User user = userMapper.toEntity(jwtRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of((Role.USER)));
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }



    @Transactional
    public JwtRequest registerAdmin(JwtRequest jwtRequest) {
        userRepository.findByUserName(jwtRequest.getName())
                .ifPresent(u -> {
                    throw new ApiAlreadyExistsException("Админ с таким именем уже существует!");
                });

        userRepository.findByEmail(jwtRequest.getEmail())
                .ifPresent(u -> {
                    throw new ApiAlreadyExistsException("Админ с таким email уже существует!");
                });

        User user = userMapper.toEntity(jwtRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of((Role.ADMIN)));
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }




    @Transactional
    public void delete(Long id)  {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Пользователь с таким id: " + id  + " не существует!"));
        userRepository.delete(user);
    }

    @Transactional
    public JwtRequest update(Long id, JwtRequest jwtRequest) {
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Пользователь с таким id: " + id + " не существует!"));

        userRepository.findByUserName(jwtRequest.getName())
                .filter(u -> !u.getId().equals(id))
                .ifPresent(u -> {
                    throw new ApiAlreadyExistsException("Пользователь с таким именем: " + jwtRequest.getName() + " уже существует!");
                });

        userRepository.findByEmail(jwtRequest.getEmail())
                .filter(u -> !u.getId().equals(id))
                .ifPresent(u -> {
                    throw new ApiAlreadyExistsException("Пользователь с таким email: " + jwtRequest.getEmail() + " уже существует!");
                });

        foundUser.setUserName(jwtRequest.getName());
        foundUser.setEmail(jwtRequest.getEmail());
        foundUser.setPassword(passwordEncoder.encode(jwtRequest.getPassword()));

        userRepository.save(foundUser);
        return userMapper.toDto(foundUser);
    }
}
