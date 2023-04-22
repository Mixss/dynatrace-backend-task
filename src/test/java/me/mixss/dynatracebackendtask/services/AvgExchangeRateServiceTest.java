package me.mixss.dynatracebackendtask.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.mixss.dynatracebackendtask.exceptions.ApiResponseBadFormatException;
import me.mixss.dynatracebackendtask.exceptions.FutureDateException;
import me.mixss.dynatracebackendtask.restclients.AvgExchangeRateClient;
import me.mixss.dynatracebackendtask.utils.DateSplitter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AvgExchangeRateServiceTest {

    AvgExchangeRateService service;
    String currencyCode, dateString, year, month, day;
    LocalDate date;

    @Mock
    AvgExchangeRateClient apiClient;
    @Mock
    DateSplitter dateSplitter;

    @BeforeEach
    void setUp()  {
        MockitoAnnotations.openMocks(this);
        service = new AvgExchangeRateService(apiClient, dateSplitter);
        dateString = "2020-04-09";
        date = LocalDate.parse(dateString);
        currencyCode = "usd";
        year = "2020";
        month = "04";
        day = "09";
    }

    @Test
    void getAvgExchangeRateAtDate_CorrectFloat_apiClientProperResponse() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        float correctValue = 4.1744f;
        JsonNode apiGoodResult = mapper.readTree("{\"table\":\"A\",\"currency\":\"dolar amerykański\",\"code\":\"USD\",\"rates\":[{\"no\":\"070/A/NBP/2020\",\"effectiveDate\":\"2020-04-09\",\"mid\":4.1744}]}");

        when(dateSplitter.getYear(date)).thenReturn(year);
        when(dateSplitter.getMonth(date)).thenReturn(month);
        when(dateSplitter.getDay(date)).thenReturn(day);
        when(apiClient.getAvgExchangeRateAtDate(currencyCode, year, month, day)).thenReturn(apiGoodResult);

        float serviceResult = service.getAvgExchangeRateAtDate(currencyCode, dateString);

        assertEquals(correctValue, serviceResult);
    }

    @Test
    void getAvgExchangeRateAtDate_throwsApiResponseBadFormatException_ratesNotPresent() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode ratesNotPresentApiResult = mapper.readTree("{}");

        when(dateSplitter.getYear(date)).thenReturn(year);
        when(dateSplitter.getMonth(date)).thenReturn(month);
        when(dateSplitter.getDay(date)).thenReturn(day);
        when(apiClient.getAvgExchangeRateAtDate(currencyCode, year, month, day)).thenReturn(ratesNotPresentApiResult);

        Assertions.assertThrows(ApiResponseBadFormatException.class, () -> {
            service.getAvgExchangeRateAtDate(currencyCode, dateString);
        }, "Should throw ApiResponseBadFormatException");
    }

    @Test
    void getAvgExchangeRateAtDate_throwsApiResponseBadFormatException_midNotPresent() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode ratesNotPresentApiResult = mapper.readTree("{\"table\":\"A\",\"currency\":\"dolar amerykański\",\"code\":\"USD\",\"rates\":[{\"no\":\"070/A/NBP/2020\",\"effectiveDate\":\"2020-04-09\",\"avg\":4.1744}]}");

        when(dateSplitter.getYear(date)).thenReturn(year);
        when(dateSplitter.getMonth(date)).thenReturn(month);
        when(dateSplitter.getDay(date)).thenReturn(day);
        when(apiClient.getAvgExchangeRateAtDate(currencyCode, year, month, day)).thenReturn(ratesNotPresentApiResult);

        Assertions.assertThrows(ApiResponseBadFormatException.class, () -> {
            service.getAvgExchangeRateAtDate(currencyCode, dateString);
        }, "Should throw ApiResponseBadFormatException");
    }

    @Test
    void getAvgExchangeRateAtDate_throwsFutureDateException_futureDateInDateString(){
        Assertions.assertThrows(FutureDateException.class, () -> {
            service.getAvgExchangeRateAtDate(currencyCode, "3000-05-05");
        }, "Should throw FutureDateException");
    }
}