package com.example.arithmetic.service.impl;

import com.example.arithmetic.controller.objects.CalculationInput;
import com.example.arithmetic.repository.CalculationRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
public class CalculationServiceImplTest {


    private final CalculationRepository calculationRepository = mock(CalculationRepository.class);
    private final CalculationServiceImpl calculationServiceImpl = new CalculationServiceImpl(calculationRepository);

    @Nested
    class TestOnlyRomanNumeralsInput {
        @Test
        public void performCalculation_WhenAddWithInputRomanNumerals_ShouldReturnDecimalAndRomanNumeral() {
            CalculationInput calculationInput = new CalculationInput("XX","X", com.example.arithmetic.data.Book.Operator.ADD);

            when(calculationRepository.save(Mockito.any(com.example.arithmetic.data.Book.class)))
                    .thenAnswer(i -> i.getArguments()[0]);

            // ---
            com.example.arithmetic.data.Book result = calculationServiceImpl.performCalculation(calculationInput);
            // ---

            assertEquals("XXX", result.getOutputRoman());
            assertEquals(30, result.getOutputArabic());
            assertNotNull(result);
        }

        @Test
        public void performCalculation_WhenSubtractWithInputRomanNumerals_ShouldReturnDecimalAndRomanNumeral() {
            CalculationInput calculationInput = new CalculationInput("XX","X", com.example.arithmetic.data.Book.Operator.SUBTRACT);

            when(calculationRepository.save(Mockito.any(com.example.arithmetic.data.Book.class)))
                    .thenAnswer(i -> i.getArguments()[0]);

            // ---
            com.example.arithmetic.data.Book result = calculationServiceImpl.performCalculation(calculationInput);
            // ---

            assertEquals("X", result.getOutputRoman());
            assertEquals(10, result.getOutputArabic());
            assertNotNull(result);
        }

        @Test
        public void performCalculation_WhenMultipleWithInputRomanNumerals_ShouldReturnDecimalAndRomanNumeral() {
            CalculationInput calculationInput = new CalculationInput("XX","X", com.example.arithmetic.data.Book.Operator.MULTIPLY);

            when(calculationRepository.save(Mockito.any(com.example.arithmetic.data.Book.class)))
                    .thenAnswer(i -> i.getArguments()[0]);

            // ---
            com.example.arithmetic.data.Book result = calculationServiceImpl.performCalculation(calculationInput);
            // ---

            assertEquals("CC", result.getOutputRoman());
            assertEquals(200, result.getOutputArabic());
            assertNotNull(result);
        }

        @Test
        public void performCalculation_WhenDivideWithInputRomanNumerals_ShouldReturnDecimalAndRomanNumeral() {
            CalculationInput calculationInput = new CalculationInput("XX","X", com.example.arithmetic.data.Book.Operator.DIVIDE);

            when(calculationRepository.save(Mockito.any(com.example.arithmetic.data.Book.class)))
                    .thenAnswer(i -> i.getArguments()[0]);

            // ---
            com.example.arithmetic.data.Book result = calculationServiceImpl.performCalculation(calculationInput);
            // ---

            assertEquals("II", result.getOutputRoman());
            assertEquals(2, result.getOutputArabic());
            assertNotNull(result);
        }

    }

    @Nested
    class TestOnlyArabicNumeralsInput {
        @Test
        public void performCalculation_WhenAddWithInputArabicNumerals_ShouldReturnDecimalAndRomanNumeral() {
            CalculationInput calculationInput = new CalculationInput("200","300", com.example.arithmetic.data.Book.Operator.ADD);

            when(calculationRepository.save(Mockito.any(com.example.arithmetic.data.Book.class)))
                    .thenAnswer(i -> i.getArguments()[0]);

            // ---
            com.example.arithmetic.data.Book result = calculationServiceImpl.performCalculation(calculationInput);
            // ---

            assertEquals("D", result.getOutputRoman());
            assertEquals(500, result.getOutputArabic());
            assertNotNull(result);
        }

        @Test
        public void performCalculation_WhenSubtractWithInputArabicNumerals_ShouldReturnDecimalAndRomanNumeral() {
            CalculationInput calculationInput = new CalculationInput("2590","173", com.example.arithmetic.data.Book.Operator.SUBTRACT);

            when(calculationRepository.save(Mockito.any(com.example.arithmetic.data.Book.class)))
                    .thenAnswer(i -> i.getArguments()[0]);

            // ---
            com.example.arithmetic.data.Book result = calculationServiceImpl.performCalculation(calculationInput);
            // ---

            assertEquals("MMCDXVII", result.getOutputRoman());
            assertEquals(2417, result.getOutputArabic());
            assertNotNull(result);
        }

        @Test
        public void performCalculation_WhenMultipleWithInputArabicNumerals_ShouldReturnDecimalAndRomanNumeral() {
            CalculationInput calculationInput = new CalculationInput("45","95", com.example.arithmetic.data.Book.Operator.MULTIPLY);
            when(calculationRepository.save(Mockito.any(com.example.arithmetic.data.Book.class)))
                    .thenAnswer(i -> i.getArguments()[0]);

            // ---
            com.example.arithmetic.data.Book result = calculationServiceImpl.performCalculation(calculationInput);
            // ---

            assertNull(result.getOutputRoman());
            assertEquals(4275, result.getOutputArabic());
            assertNotNull(result);
        }

        @Test
        public void performCalculation_WhenDivideWithInputArabicNumerals_ShouldReturnDecimalAndRomanNumeral() {

            CalculationInput calculationInput = new CalculationInput("999","333", com.example.arithmetic.data.Book.Operator.DIVIDE);

            when(calculationRepository.save(Mockito.any(com.example.arithmetic.data.Book.class)))
                    .thenAnswer(i -> i.getArguments()[0]);

            // ---
            com.example.arithmetic.data.Book result = calculationServiceImpl.performCalculation(calculationInput);
            // ---

            assertEquals("III", result.getOutputRoman());
            assertEquals(3, result.getOutputArabic());
            assertNotNull(result);
        }

    }

    @Nested
    class TestMixedArabicAndRomanNumeralsInput {
        @Test
        public void performCalculation_WhenAddWithInputArabicAndRomanNumerals_ShouldReturnDecimalAndRomanNumeral() {

            CalculationInput calculationInput = new CalculationInput("200","XCV", com.example.arithmetic.data.Book.Operator.ADD);

            when(calculationRepository.save(Mockito.any(com.example.arithmetic.data.Book.class)))
                    .thenAnswer(i -> i.getArguments()[0]);

            // ---
            com.example.arithmetic.data.Book result = calculationServiceImpl.performCalculation(calculationInput);
            // ---

            assertEquals("CCXCV", result.getOutputRoman());
            assertEquals(295, result.getOutputArabic());
            assertNotNull(result);
        }

        @Test
        public void performCalculation_WhenSubtractWithInputArabicAndRomanNumerals_ShouldReturnDecimalAndRomanNumeral() {

            CalculationInput calculationInput = new CalculationInput("XXMMM","173", com.example.arithmetic.data.Book.Operator.SUBTRACT);

            when(calculationRepository.save(Mockito.any(com.example.arithmetic.data.Book.class)))
                    .thenAnswer(i -> i.getArguments()[0]);

            // ---
            com.example.arithmetic.data.Book result = calculationServiceImpl.performCalculation(calculationInput);
            // ---

            assertEquals("MMDCCCXXVII", result.getOutputRoman());
            assertEquals(2827, result.getOutputArabic());
            assertNotNull(result);
        }

        @Test
        public void performCalculation_WhenMultipleWithInputArabicAndRomanNumerals_ShouldReturnDecimalAndRomanNumeral() {

            CalculationInput calculationInput = new CalculationInput("XV","95", com.example.arithmetic.data.Book.Operator.MULTIPLY);

            when(calculationRepository.save(Mockito.any(com.example.arithmetic.data.Book.class)))
                    .thenAnswer(i -> i.getArguments()[0]);

            // ---
            com.example.arithmetic.data.Book result = calculationServiceImpl.performCalculation(calculationInput);
            // ---

            assertEquals("MCDXXV", result.getOutputRoman());
            assertEquals(1425, result.getOutputArabic());
            assertNotNull(result);
        }

        @Test
        public void performCalculation_WhenDivideWithInputArabicAndRomanNumerals_ShouldReturnDecimalAndRomanNumeral() {

            CalculationInput calculationInput = new CalculationInput("999","X", com.example.arithmetic.data.Book.Operator.DIVIDE);

            when(calculationRepository.save(Mockito.any(com.example.arithmetic.data.Book.class)))
                    .thenAnswer(i -> i.getArguments()[0]);

            // ---
            com.example.arithmetic.data.Book result = calculationServiceImpl.performCalculation(calculationInput);
            // ---

            assertEquals("XCIX", result.getOutputRoman());
            assertEquals(99, result.getOutputArabic());
            assertNotNull(result);
        }

    }

    @Nested
    class TestExceptions {

        @Test
        public void performCalculation_WhenInputGivenasZero_ShouldThrowException() {

            CalculationInput calculationInput = new CalculationInput("0","1", com.example.arithmetic.data.Book.Operator.DIVIDE);

            when(calculationRepository.save(Mockito.any(com.example.arithmetic.data.Book.class)))
                    .thenAnswer(i -> i.getArguments()[0]);

            // ---
            com.example.arithmetic.data.Book result = calculationServiceImpl.performCalculation(calculationInput);
            // ---

            System.out.println("result " + result);

            assertNull(result.getOutputRoman());
            assertEquals(0, result.getOutputArabic());
            assertNotNull(result);
        }
    }

    @Nested
    class TestNegativeResult {

        @Test
        public void performCalculation_WhenAddedWithInputArabicNumerals_ShouldReturnDecimalAndRomanNumeral() {

            CalculationInput calculationInput = new CalculationInput("20","-30", com.example.arithmetic.data.Book.Operator.ADD);

            when(calculationRepository.save(Mockito.any(com.example.arithmetic.data.Book.class)))
                    .thenAnswer(i -> i.getArguments()[0]);

            // ---
            com.example.arithmetic.data.Book result = calculationServiceImpl.performCalculation(calculationInput);
            // ---

            assertNull(result.getOutputRoman());
            assertEquals(-10, result.getOutputArabic());
            assertNotNull(result);
        }

    }

}
