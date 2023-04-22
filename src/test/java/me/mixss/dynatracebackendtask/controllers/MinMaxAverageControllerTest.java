package me.mixss.dynatracebackendtask.controllers;

import me.mixss.dynatracebackendtask.domain.MinMaxAverageResult;
import me.mixss.dynatracebackendtask.services.MaxDiffBuyAndSellRateService;
import me.mixss.dynatracebackendtask.services.MinMaxAverageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MinMaxAverageControllerTest {

    MinMaxAverageController controller;
    String currencyCode = "gbp";
    int numberOfQuotations = 5;

    @Mock
    MinMaxAverageService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new MinMaxAverageController(service);
    }

    @Test
    void getMinMax_2d52d6_serviceReturns2d52d6() {
        MinMaxAverageResult correctResult = new MinMaxAverageResult(2.5f, 2.6f);
        when(service.getLastMinMaxAverage(currencyCode, numberOfQuotations)).thenReturn(correctResult);

        assertEquals(correctResult, controller.getMinMax(currencyCode, numberOfQuotations));
    }

}