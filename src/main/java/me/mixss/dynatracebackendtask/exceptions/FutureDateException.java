package me.mixss.dynatracebackendtask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FutureDateException extends RuntimeException{
    public FutureDateException() {
        super("Selected date is in the future!");
    }
}
