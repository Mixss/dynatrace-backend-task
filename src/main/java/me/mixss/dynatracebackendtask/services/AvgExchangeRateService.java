package me.mixss.dynatracebackendtask.services;

import com.fasterxml.jackson.databind.JsonNode;
import me.mixss.dynatracebackendtask.exceptions.BadDateFormatException;
import me.mixss.dynatracebackendtask.restclients.AvgExchangeRateClient;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class AvgExchangeRateService {

    private final AvgExchangeRateClient avgExchangeRateClient;

    public AvgExchangeRateService(AvgExchangeRateClient avgExchangeRateClient) {
        this.avgExchangeRateClient = avgExchangeRateClient;
    }

    public Float getAvgExchangeRateAtDate(String currencyCode, String dateString) {

        try {
            LocalDate date = LocalDate.parse(dateString);
            DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
            DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");

            String month = date.format(monthFormatter);
            String day = date.format(dayFormatter);

            // npb api call
            JsonNode result = avgExchangeRateClient.getAvgExchangeRateAtDate(currencyCode, String.valueOf(date.getYear()), month, day);
            JsonNode rates = result.path("rates");

            float value = 0f;
            int len = 0;

            // rates is always an array
            for(JsonNode rate : rates){
                value += Float.parseFloat(String.valueOf(rate.path("mid")));
                len++;
            }
            value /= len;
            return value;

        }
        catch (DateTimeParseException e){
            throw new BadDateFormatException();
        }

    }
}
