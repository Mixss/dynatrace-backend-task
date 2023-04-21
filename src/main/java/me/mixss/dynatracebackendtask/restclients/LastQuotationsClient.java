package me.mixss.dynatracebackendtask.restclients;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

@Component
public class LastQuotationsClient extends ApiClient{
    public JsonNode getLastQuotations(String currencyCode, int numberOfQuotations){
        String apiUrl = String.format("http://api.nbp.pl/api/exchangerates/rates/a/%s/last/%d/",
                                        currencyCode, numberOfQuotations);
        return makeCall(apiUrl);
    }
}
