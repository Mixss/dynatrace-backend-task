package me.mixss.dynatracebackendtask.services;

import com.fasterxml.jackson.databind.JsonNode;
import me.mixss.dynatracebackendtask.exceptions.ApiResponseBadFormatException;
import me.mixss.dynatracebackendtask.exceptions.WrongNumberOfQuotations;
import me.mixss.dynatracebackendtask.restclients.LastQuotationsBuyAndSellRatesClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class MaxDiffBuyAndSellRateService {

    private final int maxNumberOfQuotations = 255;
    private final LastQuotationsBuyAndSellRatesClient lastQuotationsBuyAndSellRatesClient;

    public MaxDiffBuyAndSellRateService(LastQuotationsBuyAndSellRatesClient lastQuotationsBuyAndSellRatesClient) {
        this.lastQuotationsBuyAndSellRatesClient = lastQuotationsBuyAndSellRatesClient;
    }

    public Double getMaxDiffBuyAndSellRate(String currencyCode, int numberOfLastQuotations){
        if(numberOfLastQuotations > maxNumberOfQuotations || numberOfLastQuotations < 1){
            throw new WrongNumberOfQuotations();
        }

        JsonNode result = lastQuotationsBuyAndSellRatesClient.getLastQuotationsBuyAndSellRates(currencyCode, numberOfLastQuotations);
        JsonNode rates = result.path("rates");
        if(rates.isEmpty())
            throw new ApiResponseBadFormatException();

        double maxDiff = 0;

        for(JsonNode rate : rates){
            JsonNode bid = rate.path("bid");
            JsonNode ask = rate.path("ask");
            if(!bid.isDouble() || !ask.isDouble())
                throw new ApiResponseBadFormatException();
            double bidVal = Double.parseDouble(String.valueOf(bid));
            double askVal = Double.parseDouble(String.valueOf(ask));
            double diff = Math.abs(askVal - bidVal);
            // rounding the number to four decimal spaces
            BigDecimal bd = new BigDecimal(Double.toString(diff));
            bd = bd.setScale(4, RoundingMode.HALF_UP);
            diff = bd.doubleValue();

            if (diff > maxDiff)
                maxDiff = diff;
        }

        return maxDiff;
    }
}
