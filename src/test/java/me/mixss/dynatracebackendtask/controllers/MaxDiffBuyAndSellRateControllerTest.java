package me.mixss.dynatracebackendtask.controllers;

import me.mixss.dynatracebackendtask.services.AvgExchangeRateService;
import me.mixss.dynatracebackendtask.services.MaxDiffBuyAndSellRateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MaxDiffBuyAndSellRateControllerTest {
    MaxDiffBuyAndSellRateController controller;
    String currencyCode = "gbp";
    int numberOfQuotations = 5;

    @Mock
    MaxDiffBuyAndSellRateService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new MaxDiffBuyAndSellRateController(service);
    }

    @Test
    void getMaxDiffBuyAndSellRate_0d3333_serviceReturns0d3333() {
        double correctValue = 0.3333;
        when(service.getMaxDiffBuyAndSellRate(currencyCode, numberOfQuotations)).thenReturn(correctValue);

        assertEquals(correctValue, controller.getMaxDiffBuyAndSellRate(currencyCode, numberOfQuotations));
    }

}