package me.mixss.dynatracebackendtask.restclients;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

@Component
public class AvgExchangeRateClient extends ApiClient{

    public JsonNode getAvgExchangeRateAtDate(String currencyCode, String year, String month, String day){
        String apiUrl = String.format("http://api.nbp.pl/api/exchangerates/rates/a/%s/%s-%s-%s",
                currencyCode, year, month, day);
        return makeCall(apiUrl);
    }
}
