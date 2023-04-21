package me.mixss.dynatracebackendtask.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.mixss.dynatracebackendtask.exceptions.BadDateFormatException;
import me.mixss.dynatracebackendtask.restclients.AvgExchangeRateClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AvgExchangeRateServiceTest {

    AvgExchangeRateService service;
    JsonNode apiResult;
    float correctValue;
    String currencyCode, date, year, month, day;

    @Mock
    AvgExchangeRateClient apiClient;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        MockitoAnnotations.openMocks(this);
        service = new AvgExchangeRateService(apiClient);
        ObjectMapper mapper = new ObjectMapper();
        correctValue = 4.1744f;
        apiResult = mapper.readTree("{\"table\":\"A\",\"currency\":\"dolar amerykański\",\"code\":\"USD\",\"rates\":[{\"no\":\"070/A/NBP/2020\",\"effectiveDate\":\"2020-04-09\",\"mid\":4.1744}]}");
        date = "2020-04-09";
        currencyCode = "usd";
        year = "2020";
        month = "04";
        day = "09";
    }

    @Test
    void getAvgExchangeRateAtDate_CorrectFloat_apiClientProperResponse() {

        when(apiClient.getAvgExchangeRateAtDate(currencyCode, year, month, day)).thenReturn(apiResult);

        float serviceResult = service.getAvgExchangeRateAtDate(currencyCode, "2020-04-09");

        assertEquals(correctValue, serviceResult);
    }

    @Test
    void getAvgExchangeRateAtDate_throwsBadDateFormatException_WrongDateFormat() {
        Assertions.assertThrows(BadDateFormatException.class, () -> {
            service.getAvgExchangeRateAtDate(currencyCode, "2020-04-9");
        }, "Should throw BadDateFormatException when 2020-04-9");
    }
}