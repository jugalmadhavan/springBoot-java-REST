package com.example.arithmetic.controller.helpers;

import com.example.arithmetic.controller.objects.CalculationInput;
import com.example.arithmetic.service.CalculationService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.mock;

class CalculateAndRespondTest {

    private final CalculationService calculationService = mock(CalculationService.class);
    private final com.example.arithmetic.controller.helpers.CreateBookAndRespond createBookAndRespond = new com.example.arithmetic.controller.helpers.CreateBookAndRespond(calculationService);

    @Test
    void getOperationResultResponseEntity_WhenInvalidRomanPattern_ShouldReturnBadRequest() {

        CalculationInput calculationInput = new CalculationInput("INVALID", "INVALID", com.example.arithmetic.data.Book.Operator.ADD);

        // ---
        ResponseEntity<com.example.arithmetic.data.Book> result = createBookAndRespond.getOperationResultResponseEntity(calculationInput);
        // ---

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void getOperationResultResponseEntity_WhenInputOutOfRange_ShouldReturnBadRequest() {

        CalculationInput calculationInput = new CalculationInput("-100", "10", com.example.arithmetic.data.Book.Operator.ADD);

        // ---
        ResponseEntity<com.example.arithmetic.data.Book> result = createBookAndRespond.getOperationResultResponseEntity(calculationInput);
        // ---

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void getOperationResultResponseEntity_WhenInputOutOfRange_ShouldReturnStatusOK() {

        CalculationInput calculationInput = new CalculationInput("100", "10", com.example.arithmetic.data.Book.Operator.ADD);

        // ---
        ResponseEntity<com.example.arithmetic.data.Book> result = createBookAndRespond.getOperationResultResponseEntity(calculationInput);
        // ---

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}