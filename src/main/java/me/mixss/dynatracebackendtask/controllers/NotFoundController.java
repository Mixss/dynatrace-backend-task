package me.mixss.dynatracebackendtask.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import me.mixss.dynatracebackendtask.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@Hidden
public class NotFoundController {

    @GetMapping("/api/**")
    public String notFound(){
        throw new NotFoundException();
    }
}
