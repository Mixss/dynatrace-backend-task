package me.mixss.dynatracebackendtask.controllers;

import io.swagger.v3.oas.annotations.Operation;
import me.mixss.dynatracebackendtask.domain.MinMaxAverageResult;
import me.mixss.dynatracebackendtask.services.MinMaxAverageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MinMaxAverageController {

    private final MinMaxAverageService minMaxAverageService;

    public MinMaxAverageController(MinMaxAverageService minMaxAverageService) {
        this.minMaxAverageService = minMaxAverageService;
    }

    @GetMapping("/minmax/{currencyCode}/{numberOfLastQuotations}")
    @Operation(tags = "Minimum and maximum value of the currency in the last N(<=255) quotations")
    public MinMaxAverageResult getMinMax(@PathVariable String currencyCode, @PathVariable int numberOfLastQuotations){
        return minMaxAverageService.getLastMinMaxAverage(currencyCode, numberOfLastQuotations);
    }
}
