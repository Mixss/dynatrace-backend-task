package me.mixss.dynatracebackendtask.controllers;

import me.mixss.dynatracebackendtask.services.MaxDiffBuyAndSellRateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MaxDiffBuyAndSellRateController {

    private final MaxDiffBuyAndSellRateService maxDiffBuyAndSellRateService;

    public MaxDiffBuyAndSellRateController(MaxDiffBuyAndSellRateService maxDiffBuyAndSellRateService) {
        this.maxDiffBuyAndSellRateService = maxDiffBuyAndSellRateService;
    }

    @GetMapping("/maxdiff/{currencyCode}/{numberOfQuotations}")
    public Float getMaxDiffBuyAndSellRate(@PathVariable String currencyCode, @PathVariable int numberOfQuotations){
        return maxDiffBuyAndSellRateService.getMaxDiffBuyAndSellRate(currencyCode, numberOfQuotations);
    }
}
