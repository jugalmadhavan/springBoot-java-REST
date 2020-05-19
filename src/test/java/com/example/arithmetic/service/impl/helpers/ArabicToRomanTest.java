package com.example.arithmetic.service.impl.helpers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArabicToRomanTest {


    @Test
    public void convert_WhenInputArabicNumeral_ShouldReturnRomanNumeral() {
        ArabicToRoman atr = new ArabicToRoman();
        int arabicInput = 1904;

        // ---
        String result = atr.convert(arabicInput);
        // ---

        assertEquals("MCMIV", result);
        assertNotNull(result);

        arabicInput = 500;

        // ---
        result = atr.convert(arabicInput);
        // ---

        assertEquals("D", result);
        assertNotNull(result);

        arabicInput = 1000;

        // ---
        result = atr.convert(arabicInput);
        // ---

        assertEquals("M", result);
        assertNotNull(result);

        arabicInput = 3999;

        // ---
        result = atr.convert(arabicInput);
        // ---

        assertEquals("MMMCMXCIX", result);
        assertNotNull(result);

        arabicInput = 4346;

        // ---
        result = atr.convert(arabicInput);
        // ---

        assertEquals("OVERFLOW", result);
        assertNotNull(result);

    }

    @Test
    public void convert_WhenInputNegativeArabicNumeral_ShouldReturnRomanNumeral() throws Exception {
        ArabicToRoman atr = new ArabicToRoman();
        int arabicInput = -10;

        // ---
        String result = atr.convert(arabicInput);
        // ---

        assertEquals("NEGATIVE", result);
        assertNotNull(result);
    }

}
