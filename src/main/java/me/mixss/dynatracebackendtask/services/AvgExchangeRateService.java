package me.mixss.dynatracebackendtask.services;

import com.fasterxml.jackson.databind.JsonNode;
import me.mixss.dynatracebackendtask.exceptions.ApiResponseBadFormatException;
import me.mixss.dynatracebackendtask.restclients.AvgExchangeRateClient;
import me.mixss.dynatracebackendtask.utils.DateSplitter;
import org.springframework.stereotype.Service;


@Service
public class AvgExchangeRateService {

    private final AvgExchangeRateClient avgExchangeRateClient;
    private final DateSplitter dateSplitter;

    public AvgExchangeRateService(AvgExchangeRateClient avgExchangeRateClient, DateSplitter dateSplitter) {
        this.avgExchangeRateClient = avgExchangeRateClient;
        this.dateSplitter = dateSplitter;
    }

    public Float getAvgExchangeRateAtDate(String currencyCode, String dateString) {

        // npb api call
        JsonNode result = avgExchangeRateClient.getAvgExchangeRateAtDate(currencyCode,
                dateSplitter.getYear(dateString), dateSplitter.getMonth(dateString), dateSplitter.getDay(dateString));
        JsonNode rates = result.path("rates");
        if(rates.isEmpty())
            throw new ApiResponseBadFormatException();

        float value = 0f;
        int len = 0;

        // rates is always an array, in case of many elements present the average is computed
        for(JsonNode rate : rates){
            JsonNode mid = rate.path("mid");
            if(!mid.isDouble())
                throw new ApiResponseBadFormatException();
            value += Float.parseFloat(String.valueOf(mid));
            len++;
        }
        value /= len;
        return value;
    }
}
