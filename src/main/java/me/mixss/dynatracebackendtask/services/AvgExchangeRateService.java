package me.mixss.dynatracebackendtask.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class AvgExchangeRateService {

    public Float getAvgExchangeRateAtDate(String currencyCode, String dateString) throws JsonProcessingException {

        LocalDate date = LocalDate.parse(dateString);
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");

        String month = date.format(monthFormatter);
        String day = date.format(dayFormatter);

        String apiUrl = String.format("http://api.nbp.pl/api/exchangerates/rates/a/%s/%d-%s-%s?format=json",
                currencyCode, date.getYear(), month, day);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode rates = root.path("rates");

        float value = 0f;
        int len = 0;
        for(JsonNode rate : rates){
            value += Float.parseFloat(String.valueOf(rate.path("mid")));
            len++;
        }
        value /= len;
        return value;
    }
}
