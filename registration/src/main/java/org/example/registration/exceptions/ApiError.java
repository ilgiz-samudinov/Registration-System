package org.example.registration.exceptions;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class ApiError {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    // Дополнительное поле для ошибок валидации (если есть)
    private List<Map<String, String>> errors;
}
