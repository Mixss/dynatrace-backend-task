package me.mixss.dynatracebackendtask.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.mixss.dynatracebackendtask.domain.MinMaxAverageResult;
import me.mixss.dynatracebackendtask.exceptions.ApiResponseBadFormatException;
import me.mixss.dynatracebackendtask.restclients.LastQuotationsClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MinMaxAverageServiceTest {

    MinMaxAverageService service;

    @Mock
    LastQuotationsClient apiClient;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        service = new MinMaxAverageService(apiClient);
    }

    @Test
    void getLastMinMaxAverage_CorrectResult_CorrectData() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode apiResult = mapper.readTree("{\"table\":\"A\",\"currency\":\"dolar amerykański\",\"code\":\"USD\",\"rates\":[{\"no\":\"074/A/NBP/2023\",\"effectiveDate\":\"2023-04-17\",\"mid\":4.2261},{\"no\":\"075/A/NBP/2023\",\"effectiveDate\":\"2023-04-18\",\"mid\":4.2151},{\"no\":\"076/A/NBP/2023\",\"effectiveDate\":\"2023-04-19\",\"mid\":4.2244},{\"no\":\"077/A/NBP/2023\",\"effectiveDate\":\"2023-04-20\",\"mid\":4.2024},{\"no\":\"078/A/NBP/2023\",\"effectiveDate\":\"2023-04-21\",\"mid\":4.2006}]}");
        String currencyCode = "usd";
        int numberOfQuotations = 5;
        MinMaxAverageResult correctResult = new MinMaxAverageResult(4.2006f, 4.2261f);

        when(apiClient.getLastQuotations(currencyCode, numberOfQuotations)).thenReturn(apiResult);

        assertEquals(correctResult, service.getLastMinMaxAverage(currencyCode, numberOfQuotations));

    }
    @Test
    void getLastMinMaxAverage_throwsApiBadFormatException_NoRates() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode apiResult = mapper.readTree("{}");
        String currencyCode = "usd";
        int numberOfQuotations = 5;

        when(apiClient.getLastQuotations(currencyCode, numberOfQuotations)).thenReturn(apiResult);

        Assertions.assertThrows(ApiResponseBadFormatException.class, () -> {
            service.getLastMinMaxAverage(currencyCode, numberOfQuotations);
        }, "Should throw ApiResponseBadFormatException");
    }

}