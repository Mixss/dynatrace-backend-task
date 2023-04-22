package me.mixss.dynatracebackendtask.controllers;

import me.mixss.dynatracebackendtask.services.AvgExchangeRateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class AvgExchangeRateController {

    private final AvgExchangeRateService avgExchangeRateService;

    public AvgExchangeRateController(AvgExchangeRateService avgExchangeRateService) {
        this.avgExchangeRateService = avgExchangeRateService;
    }

    @GetMapping ("/avg/{currencyCode}/{date}")
    public Float getAvgExchangeRateAtDay(@PathVariable String currencyCode, @PathVariable String date ) {
        return avgExchangeRateService.getAvgExchangeRateAtDate(currencyCode, date);
    }
}
