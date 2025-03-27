package org.example.registration.mappers;

import lombok.RequiredArgsConstructor;
import org.example.registration.dto.JwtRequest;
import org.example.registration.entities.User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public JwtRequest toDto(User entity){
        return JwtRequest.builder()
                .name(entity.getUserName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .build();
    }

    public User toEntity(JwtRequest dto){
        return User.builder()
                .userName(dto.getName())
                .email(dto.getEmail())
                .password((dto.getPassword()))
                .build();
    }

}
