package org.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResidentNotFoundException extends RuntimeException {
    public ResidentNotFoundException() {
        super();
    }

    public ResidentNotFoundException(String message) {
        super(message);
        log.info("ResidentNotFoundException {}", message);
    }
}
