package me.mixss.dynatracebackendtask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadDateFormatException extends RuntimeException{

    //this exception is thrown when given date is in bad format
    public BadDateFormatException() {
        super("Provided date is in bad format");
    }
}
