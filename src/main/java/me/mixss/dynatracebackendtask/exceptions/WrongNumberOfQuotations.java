package me.mixss.dynatracebackendtask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongNumberOfQuotations extends RuntimeException{

    // this exception is thrown when given number of quotation is bigger than 255 or below 1
    public WrongNumberOfQuotations() {
        super("Wrong number of quotations. The value should be between 1 and 255");
    }
}
