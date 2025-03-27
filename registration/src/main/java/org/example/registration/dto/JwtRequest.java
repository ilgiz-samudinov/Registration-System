package org.example.registration.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtRequest {

    private Long id;
    @NotBlank(message = "Имя не должно быть пустым")
    @Size(min = 3, max = 50, message = "Имя должно содержать от 3 до 50 символов")
    private String name;

    @NotBlank(message = "Email не должен быть пустым")
    @Email(message = "Некорректный формат email")
    @Size(max = 100, message = "Email должен содержать не более 100 символов")
    private String email;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 8, message = "Пароль должен содержать не менее 8 символов")
    private String password;



}
