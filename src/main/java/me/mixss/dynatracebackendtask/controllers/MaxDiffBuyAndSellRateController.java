package me.mixss.dynatracebackendtask.controllers;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(tags = "Major difference between the buy and ask rate in the last N(<=255) quotations")
    public Double getMaxDiffBuyAndSellRate(@PathVariable String currencyCode, @PathVariable int numberOfQuotations){
        return maxDiffBuyAndSellRateService.getMaxDiffBuyAndSellRate(currencyCode, numberOfQuotations);
    }
}
