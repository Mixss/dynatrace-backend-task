package me.mixss.dynatracebackendtask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ApiErrorResponseException extends RuntimeException{

    // this exception is thrown when outside API returns not supported error,
    // its message is then redirected to this exception
    public ApiErrorResponseException(String message) {
        super(message);
    }
}
