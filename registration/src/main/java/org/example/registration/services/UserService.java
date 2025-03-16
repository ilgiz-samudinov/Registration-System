package org.example.registration.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.registration.dto.UserDto;
import org.example.registration.entities.User;
import org.example.registration.exceptions.UserAlreadyExistsException;
import org.example.registration.exceptions.UserNotFoundException;
import org.example.registration.mappers.UserMapper;
import org.example.registration.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public UserDto save(UserDto userDto) {
        userRepository.findByName(userDto.getName())
                .ifPresent(u -> {
                    throw new UserAlreadyExistsException("Пользователь с таким именем уже существует!");
                });

        userRepository.findByEmail(userDto.getEmail())
                .ifPresent(u -> {
                    throw new UserAlreadyExistsException("Пользователь с таким email уже существует!");
                });

        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Хеширование пароля

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Transactional
    public void delete(Long id)  {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с таким id: " + id  + " не существует!"));
        userRepository.delete(user);
    }

    @Transactional
    public UserDto update(Long id, UserDto userDto) {
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с таким id: " + id + " не существует!"));

        userRepository.findByName(userDto.getName())
                .filter(u -> !u.getId().equals(id))
                .ifPresent(u -> {
                    throw new UserAlreadyExistsException("Пользователь с таким именем: " + userDto.getName() + " уже существует!");
                });

        userRepository.findByEmail(userDto.getEmail())
                .filter(u -> !u.getId().equals(id))
                .ifPresent(u -> {
                    throw new UserAlreadyExistsException("Пользователь с таким email: " + userDto.getEmail() + " уже существует!");
                });

        foundUser.setName(userDto.getName());
        foundUser.setEmail(userDto.getEmail());
        foundUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(foundUser);
        return userMapper.toDto(foundUser);
    }
}
