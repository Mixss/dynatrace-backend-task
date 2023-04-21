package me.mixss.dynatracebackendtask.services;

import com.fasterxml.jackson.databind.JsonNode;
import me.mixss.dynatracebackendtask.domain.MinMaxAverageResult;
import me.mixss.dynatracebackendtask.exceptions.ApiResponseBadFormatException;
import me.mixss.dynatracebackendtask.exceptions.TooBigNumberOfQuotations;
import me.mixss.dynatracebackendtask.restclients.LastQuotationsClient;
import org.springframework.stereotype.Service;

@Service
public class MinMaxAverageService {

    private final int maxNumberOfQuotations = 255;
    private LastQuotationsClient lastQuotationsClient;

    public MinMaxAverageService(LastQuotationsClient lastQuotationsClient) {
        this.lastQuotationsClient = lastQuotationsClient;
    }

    public MinMaxAverageResult getLastMinMaxAverage(String currencyCode, int numberOfLastQuotations){

        if(numberOfLastQuotations > maxNumberOfQuotations){
            throw new TooBigNumberOfQuotations();
        }

        JsonNode result = lastQuotationsClient.getLastQuotations(currencyCode, numberOfLastQuotations);
        JsonNode rates = result.path("rates");
        if(rates.isEmpty())
            throw new ApiResponseBadFormatException();

        float min = Float.MAX_VALUE;
        float max = 0;

        for(JsonNode rate : rates){
            JsonNode mid = rate.path("mid");
            if(!mid.isDouble())
                throw new ApiResponseBadFormatException();
            float value = Float.parseFloat(String.valueOf(mid));
            if(value > max) max = value;
            if(value < min) min = value;
        }

        return new MinMaxAverageResult(min, max);
    }
}
