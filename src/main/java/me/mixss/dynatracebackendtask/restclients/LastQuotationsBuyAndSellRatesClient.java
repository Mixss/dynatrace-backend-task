package me.mixss.dynatracebackendtask.restclients;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

@Component
public class LastQuotationsBuyAndSellRatesClient extends ApiClient{
    public JsonNode getLastQuotationsBuyAndSellRates(String currencyCode, int numberOfQuotations){
        String apiUrl = String.format("http://api.nbp.pl/api/exchangerates/rates/c/%s/last/%d/",
                currencyCode, numberOfQuotations);
        return makeCall(apiUrl);
    }
}
