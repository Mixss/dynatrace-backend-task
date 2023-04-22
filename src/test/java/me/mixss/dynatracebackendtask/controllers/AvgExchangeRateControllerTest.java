package me.mixss.dynatracebackendtask.controllers;

import me.mixss.dynatracebackendtask.services.AvgExchangeRateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AvgExchangeRateControllerTest {

    AvgExchangeRateController controller;
    String currencyCode = "gbp";
    String dateString = "2023-04-21";

    @Mock
    AvgExchangeRateService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new AvgExchangeRateController(service);
    }

    @Test
    void getAvgExchangeRateAtDay_3d3333_serviceReturns3d3333() {
        float correctValue = 3.3333f;
        when(service.getAvgExchangeRateAtDate(currencyCode, dateString)).thenReturn(correctValue);

        assertEquals(correctValue, controller.getAvgExchangeRateAtDay(currencyCode, dateString));
    }
}