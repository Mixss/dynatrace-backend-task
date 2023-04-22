package me.mixss.dynatracebackendtask.services;

import me.mixss.dynatracebackendtask.restclients.LastQuotationsBuyAndSellRatesClient;
import org.springframework.stereotype.Service;

@Service
public class MaxDiffBuyAndSellRateService {

    private final int maxNumberOfQuotations = 255;
    private final LastQuotationsBuyAndSellRatesClient lastQuotationsBuyAndSellRatesClient;

    public MaxDiffBuyAndSellRateService(LastQuotationsBuyAndSellRatesClient lastQuotationsBuyAndSellRatesClient) {
        this.lastQuotationsBuyAndSellRatesClient = lastQuotationsBuyAndSellRatesClient;
    }

    public Float getMaxDiffBuyAndSellRate(String currencyCode, int numberOfLastQuotations){
        return 0f;
    }
}
