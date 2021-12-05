package com.nicasia.rfc.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ClientException extends RuntimeException {

    public ClientException(String message) {
        super(message);
    }

}
