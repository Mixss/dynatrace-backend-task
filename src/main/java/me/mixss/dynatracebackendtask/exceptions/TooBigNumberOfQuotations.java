package me.mixss.dynatracebackendtask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TooBigNumberOfQuotations extends RuntimeException{

    public TooBigNumberOfQuotations() {
        super("Too big number of quotations. The max number is 255");
    }
}
