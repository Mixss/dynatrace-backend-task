package me.mixss.dynatracebackendtask.controllers;

import me.mixss.dynatracebackendtask.exceptions.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NotFoundController {

    @RequestMapping(value = "**")
    public String notFound(){
        throw new NotFoundException();
    }
}
