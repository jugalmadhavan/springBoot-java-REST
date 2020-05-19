package com.example.arithmetic.service.impl.helpers;

import com.example.arithmetic.utils.MapRomanSymbolToDecimal;

import java.util.HashMap;
import java.util.Map;

public class RomanToArabic {

    /*
     * Returns an arabic numeral for a corresponding roman numeral
     * @param inputRoman Roman input string for conversion
     * @return           arabic value
     */
    public int convert(String inputRoman) {
        int start = 0;
        return getResult(inputRoman, start);
    }

    // Iteration of input roman string
    private static int getResult(String inputRoman, int result) {
        MapRomanSymbolToDecimal roman = new MapRomanSymbolToDecimal();
        Map<Character, Integer> mapSymbol = roman.getSymbolMap();
        for (int i = 0; i < inputRoman.length(); i++) {
            StartConversion startConversion = new StartConversion(inputRoman, result, roman, i, mapSymbol).invoke();
            result = startConversion.getResult();
            i = startConversion.getIndex();
        }
        return result;
    }

    // Does conversion of symbols to decimal value considering current and next index symbols
    private static class StartConversion {
        private String inputRoman;
        private int result;
        private MapRomanSymbolToDecimal roman;
        private int index;
        Map<Character, Integer> mapSymbol;


        public StartConversion(String inputRoman, int result, MapRomanSymbolToDecimal roman, int index, Map<Character, Integer> mapSymbol) {
            this.inputRoman = inputRoman;
            this.result = result;
            this.roman = roman;
            this.index = index;
            this.mapSymbol = mapSymbol;
        }

        public int getResult() {
            return result;
        }

        public int getIndex() {
            return index;
        }

        public StartConversion invoke() {
            int valueAtCurrentIndex = mapSymbol.get(inputRoman.charAt(index));
            getSymbolValue(valueAtCurrentIndex);
            return this;
        }

        // Compares current symbol value with next symbol
        private void getSymbolValue(int valueAtCurrentIndex) {

            if (index + 1 < inputRoman.length()) {
                int valueAtNextIndex = mapSymbol.get(inputRoman.charAt(index + 1));

                getNextSymbolValue(valueAtCurrentIndex, valueAtNextIndex);
            } else {
                result = result + valueAtCurrentIndex;
                index++;
            }
        }

        private void getNextSymbolValue(int valueAtCurrentIndex, int valueAtNextIndex) {
            if (valueAtCurrentIndex >= valueAtNextIndex) {
                result = result + valueAtCurrentIndex;
            } else {
                result = result + valueAtNextIndex - valueAtCurrentIndex;
                index++;
            }
        }
    }
}
