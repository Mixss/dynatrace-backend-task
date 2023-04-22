package me.mixss.dynatracebackendtask.services;

import com.fasterxml.jackson.databind.JsonNode;
import me.mixss.dynatracebackendtask.exceptions.ApiResponseBadFormatException;
import me.mixss.dynatracebackendtask.exceptions.BadDateFormatException;
import me.mixss.dynatracebackendtask.exceptions.FutureDateException;
import me.mixss.dynatracebackendtask.restclients.AvgExchangeRateClient;
import me.mixss.dynatracebackendtask.utils.DateSplitter;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


@Service
public class AvgExchangeRateService {

    private final AvgExchangeRateClient avgExchangeRateClient;
    private final DateSplitter dateSplitter;

    public AvgExchangeRateService(AvgExchangeRateClient avgExchangeRateClient, DateSplitter dateSplitter) {
        this.avgExchangeRateClient = avgExchangeRateClient;
        this.dateSplitter = dateSplitter;
    }

    public Float getAvgExchangeRateAtDate(String currencyCode, String dateString) {

        try{
            LocalDate date = LocalDate.parse(dateString);
            if(date.isAfter(LocalDate.now())){
                throw new FutureDateException();
            }

            // npb api call
            JsonNode result = avgExchangeRateClient.getAvgExchangeRateAtDate(currencyCode,
                    dateSplitter.getYear(date), dateSplitter.getMonth(date), dateSplitter.getDay(date));
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
        catch(DateTimeParseException e){
            throw new BadDateFormatException();
        }
    }
}
