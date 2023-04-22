package me.mixss.dynatracebackendtask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TooBigNumberOfQuotations extends RuntimeException{

    // this exception is thrown when given number of quotation is bigger than 255
    public TooBigNumberOfQuotations() {
        super("Too big number of quotations. The max number is 255");
    }
}
