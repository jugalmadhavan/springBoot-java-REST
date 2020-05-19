package com.example.arithmetic.service.impl.helpers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RomanToArabicTest {


    @Test
    public void convert_WhenInputRomanNumeral_ShouldReturnArabicNumeral() throws Exception {
        RomanToArabic rta = new RomanToArabic();
        String romanInput = "DCLXXVIII";

        // ---
        int result = rta.convert(romanInput);
        // ---

        assertEquals(678, result);
        assertNotNull(result);

        romanInput = "M";

        // ---
        result = rta.convert(romanInput);
        // ---

        assertEquals(1000, result);
        assertNotNull(result);

        romanInput = "CMXCIX";

        // ---
        result = rta.convert(romanInput);
        // ---

        assertEquals(999, result);
        assertNotNull(result);

        romanInput = "MMMCMXCIX";

        // ---
        result = rta.convert(romanInput);
        // ---

        assertEquals(3999, result);
        assertNotNull(result);

    }


}
