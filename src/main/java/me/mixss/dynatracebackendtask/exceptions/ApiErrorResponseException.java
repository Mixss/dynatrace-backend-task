package me.mixss.dynatracebackendtask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ApiErrorResponseException extends RuntimeException{

    public ApiErrorResponseException(String message) {
        super(message);
    }
}
