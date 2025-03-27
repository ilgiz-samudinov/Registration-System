package org.example.registration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApiNotFoundException extends ApiException {
    public ApiNotFoundException(String message) {
        super(message);
    }
}
