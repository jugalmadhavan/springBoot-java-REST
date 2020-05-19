package com.example.arithmetic.utils;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class MapRomanSymbolToDecimalTest {

    @Test
    public void getSymbolMap_WhenValidInput_ShouldReturnInt() throws Exception {
        MapRomanSymbolToDecimal mrstd = new MapRomanSymbolToDecimal();
        Map<Character, Integer> mapSymbol = mrstd.getSymbolMap();
        char romanInput = 'C';
        // ---
        int result = mapSymbol.get(romanInput);
        // ---
        assertEquals(100, result);
        assertNotNull(result);

        romanInput = 'D';
        // ---
        result = mapSymbol.get(romanInput);
        // ---
        assertEquals(500, result);
        assertNotNull(result);

        romanInput = 'I';
        // ---
        result = mapSymbol.get(romanInput);
        // ---
        assertEquals(1, result);
        assertNotNull(result);

        romanInput = 'L';
        // ---
        result = mapSymbol.get(romanInput);
        // ---
        assertEquals(50, result);
        assertNotNull(result);

        romanInput = 'M';
        // ---
        result = mapSymbol.get(romanInput);
        // ---
        assertEquals(1000, result);
        assertNotNull(result);

    }

    @Test
    public void getSymbolMap_WhenInvalidInput_ShouldReturnNull() {
        MapRomanSymbolToDecimal mrstd = new MapRomanSymbolToDecimal();
        Map<Character, Integer> mapSymbol = mrstd.getSymbolMap();
        char romanInput = 'A';

        // ---
        Integer result = mapSymbol.get(romanInput);
        // ---
        assertEquals(null, result);

    }

}
