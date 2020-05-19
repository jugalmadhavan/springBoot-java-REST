package com.example.arithmetic.service.impl.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class ArabicToRoman {

    Logger logger = LoggerFactory.getLogger(ArabicToRoman.class);
    public static final String ZERO = "ZERO";
    public static final String OVERFLOW = "OVERFLOW";
    public static final String NEGATIVE = "NEGATIVE";
    static final int ZERO_VALUE = 0;
    static final int MAX_VALUE = 3999;

    /*
     * Returns a roman numeral for a corresponding arabic numeral
     * @param inputArabic Arabic input for conversion
     * @return            roman value
     */
    public String convert(int inputArabic) {
        char c[] = new char[10000];
        int index = 0;
        String result = checkInputValue(inputArabic);

        if (result.isEmpty()) {
            index = startConversion(inputArabic, c, index);
            AtomicReference<String> romanValue = getRomanValue(c, index);
            result = romanValue.get();
        }

        return result;
    }

    private AtomicReference<String> getRomanValue(char[] c, int i) {
        AtomicReference<String> result = new AtomicReference<>("");

        IntStream.range(0, i)
                .forEach(index ->
                {
                    result.updateAndGet(v -> v + c[index]);
                });
        return result;
    }

    private String checkInputValue(int inputArabic) {
        String errorRomanValue = "";
        if (inputArabic == ZERO_VALUE) {
            errorRomanValue = ZERO;
        } else if (inputArabic > MAX_VALUE) {
            errorRomanValue = OVERFLOW;
        } else if (inputArabic < ZERO_VALUE) {
            errorRomanValue = NEGATIVE;
        }
        return errorRomanValue;
    }

    private int startConversion(int inputArabic, char[] c, int i) {
        i = getRomanSymbol(inputArabic, c, i);
        return i;
    }

    private int getRomanSymbol(int inputArabic, char[] c, int i) {
        while (inputArabic != 0) {
            TransformBaseValue transformBaseValue = new TransformBaseValue(inputArabic, c, i).invoke();
            inputArabic = transformBaseValue.getInputArabic();
            i = transformBaseValue.getI();
        }
        return i;
    }

    private class TransformBaseValue {
        private int inputArabic;
        private char[] c;
        private int i;

        public TransformBaseValue(int inputArabic, char[] c, int i) {
            this.inputArabic = inputArabic;
            this.c = c;
            this.i = i;
        }

        public int getInputArabic() {
            return inputArabic;
        }

        public int getI() {
            return i;
        }

        public TransformBaseValue invoke() {

            if (inputArabic >= 1000) {
                forBaseValue1000();
            } else if (inputArabic >= 500) {
                forBaseValue500();
            } else if (inputArabic >= 100) {
                forBaseValue100();
            } else if (inputArabic >= 50) {
                forBaseValue50();
            } else if (inputArabic >= 10) {
                forBaseValue10();
            } else if (inputArabic >= 5) {
                forBaseValue5();
            } else if (inputArabic >= 1) {
                forBaseValue1();
            }
            return this;
        }

        // add base symbol to the character array when base value of number is greater than or equal to 1000
        private void forBaseValue1000() {
            i = digit('M', inputArabic / 1000, i, c);
            inputArabic = inputArabic % 1000;
        }

        // add base symbol to the character array when base value of number is greater than or equal to 1
        private void forBaseValue1() {
            if (inputArabic < 4) {
                i = digit('I', inputArabic, i, c);
            } else {
                i = subtractive_notation_digit('I', 'V', i, c);
            }
            inputArabic = 0;
        }

        // add base symbol to the character array when base value of number is greater than or equal to 5
        private void forBaseValue5() {
            if (inputArabic < 9) {
                i = digit('V', inputArabic / 5, i, c);
                inputArabic = inputArabic % 5;
            } else {
                i = subtractive_notation_digit('I', 'X', i, c);
                inputArabic = 0;
            }
        }

        // add base symbol to the character array when base value of number is greater than or equal to 10
        private void forBaseValue10() {
            if (inputArabic < 40) {
                i = digit('X', inputArabic / 10, i, c);
                inputArabic = inputArabic % 10;
            } else {
                i = subtractive_notation_digit('X', 'L', i, c);
                inputArabic = inputArabic % 10;
            }
        }

        // add base symbol to the character array when base value of number is greater than or equal to 500
        private void forBaseValue50() {
            if (inputArabic < 90) {
                i = digit('L', inputArabic / 50, i, c);
                inputArabic = inputArabic % 50;
            } else {
                i = subtractive_notation_digit('X', 'C', i, c);
                inputArabic = inputArabic % 10;
            }
        }

        // add base symbol to the character array when base value of number is greater than or equal to 100
        private void forBaseValue100() {
            if (inputArabic < 400) {
                i = digit('C', inputArabic / 100, i, c);
                inputArabic = inputArabic % 100;
            } else {
                i = subtractive_notation_digit('C', 'D', i, c);
                inputArabic = inputArabic % 100;
            }
        }

        // add base symbol to the character array when base value of number is greater than or equal to 500
        private void forBaseValue500() {
            if (inputArabic < 900) {
                i = digit('D', inputArabic / 500, i, c);
                inputArabic = inputArabic % 500;
            } else {
                i = subtractive_notation_digit('C', 'M', i, c);
                inputArabic = inputArabic % 100;
            }
        }

        // To add repeating symbols (n times)
        private int digit(char ch, int n, int i, char[] c) {
            for (int j = 0; j < n; j++) {
                c[i++] = ch;
            }
            return i;
        }

        // To handle subtractive notation to add corresponding symbol
        private int subtractive_notation_digit(char num1, char num2, int i, char[] c) {
            c[i++] = num1;
            c[i++] = num2;
            return i;
        }
    }
}
