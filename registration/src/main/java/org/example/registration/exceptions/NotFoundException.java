package org.example.registration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends ApiException{
    public NotFoundException(String message) {
        super(message);
    }
}
