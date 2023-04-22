package me.mixss.dynatracebackendtask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApiNotFoundException extends RuntimeException{

    // this exception is thrown when outside API returns 404 status
    public ApiNotFoundException() {
        super("Data not found");
    }
}
