package me.mixss.dynatracebackendtask.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.mixss.dynatracebackendtask.exceptions.ApiResponseBadFormatException;
import me.mixss.dynatracebackendtask.restclients.LastQuotationsBuyAndSellRatesClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MaxDiffBuyAndSellRateServiceTest {

    MaxDiffBuyAndSellRateService service;

    @Mock
    LastQuotationsBuyAndSellRatesClient apiClient;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        service = new MaxDiffBuyAndSellRateService(apiClient);
    }

    @Test
    void getMaxDiffBuyAndSellRate_CorrectDiff_CorrectData() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode apiResult = mapper.readTree("{\"table\":\"C\",\"currency\":\"dolar amerykański\",\"code\":\"USD\",\"rates\":[{\"no\":\"074/C/NBP/2023\",\"effectiveDate\":\"2023-04-17\",\"bid\":4.1626,\"ask\":4.2466},{\"no\":\"075/C/NBP/2023\",\"effectiveDate\":\"2023-04-18\",\"bid\":4.1919,\"ask\":4.2765},{\"no\":\"076/C/NBP/2023\",\"effectiveDate\":\"2023-04-19\",\"bid\":4.1769,\"ask\":4.2613},{\"no\":\"077/C/NBP/2023\",\"effectiveDate\":\"2023-04-20\",\"bid\":4.1677,\"ask\":4.2519},{\"no\":\"078/C/NBP/2023\",\"effectiveDate\":\"2023-04-21\",\"bid\":4.1532,\"ask\":4.2372}]}");
        String currencyCode = "usd";
        int numberOfQuotations = 5;
        float correctResult = 0.0846f;

        when(apiClient.getLastQuotationsBuyAndSellRates(currencyCode, numberOfQuotations)).thenReturn(apiResult);

        assertEquals(correctResult, service.getMaxDiffBuyAndSellRate(currencyCode, numberOfQuotations));
    }

    @Test
    void getMaxDiffBuyAndSellRate_throwsApiBadFormatException_NoRates() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode apiResult = mapper.readTree("{}");
        String currencyCode = "usd";
        int numberOfQuotations = 5;

        when(apiClient.getLastQuotationsBuyAndSellRates(currencyCode, numberOfQuotations)).thenReturn(apiResult);

        Assertions.assertThrows(ApiResponseBadFormatException.class, () -> {
            service.getMaxDiffBuyAndSellRate(currencyCode, numberOfQuotations);
        }, "Should throw ApiResponseBadFormatException");
    }
}