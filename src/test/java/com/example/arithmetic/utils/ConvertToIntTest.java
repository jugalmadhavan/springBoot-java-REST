package com.example.arithmetic.utils;

import com.example.arithmetic.service.impl.helpers.ArabicToRoman;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConvertToIntTest {


    @Test
    public void convert_WhenInputArabicNumeral_ShouldReturnInt() throws Exception {
        ConvertToInt cti = new ConvertToInt();
        String arabicInput = "1904";


        // ---
        int result = cti.convert(arabicInput);
        // ---

        assertEquals(1904, result);
        assertNotNull(result);

    }

    @Test
    public void convert_WhenInputRomanNumeral_ShouldReturnInt() throws Exception {
        ConvertToInt cti = new ConvertToInt();
        String input = "XV";


        // ---
        int result = cti.convert(input);
        // ---

        assertEquals(15, result);
        assertNotNull(result);

    }

    @Test
    public void convert_WhenNegativeInputArabicNumeral_ShouldReturnInt() throws Exception {
        ConvertToInt cti = new ConvertToInt();
        String arabicInput = "-20";

        // ---
        int result = cti.convert(arabicInput);
        // ---

        assertEquals(-20, result);
        assertNotNull(result);

    }

}
