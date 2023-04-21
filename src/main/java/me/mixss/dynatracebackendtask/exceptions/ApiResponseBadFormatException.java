package me.mixss.dynatracebackendtask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
public class ApiResponseBadFormatException extends RuntimeException{
    public ApiResponseBadFormatException() {
        super("Unable to provide data");
    }
}
