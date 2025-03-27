package org.example.registration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApiAlreadyExistsException extends ApiException {
    public ApiAlreadyExistsException(String message) {
        super(message);
    }
}
