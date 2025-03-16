package org.example.registration.mappers;

import lombok.RequiredArgsConstructor;
import org.example.registration.dto.UserDto;
import org.example.registration.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserDto toDto(User entity){
        return UserDto.builder()
                .name(entity.getName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .build();
    }

    public User toEntity(UserDto dto){
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password((dto.getPassword()))
                .build();
    }

}
