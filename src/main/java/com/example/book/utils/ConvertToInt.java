package com.example.arithmetic.utils;

import com.example.arithmetic.service.impl.helpers.RomanToArabic;
import org.apache.commons.lang3.math.NumberUtils;

public class ConvertToInt {

    public int convert(String input) {
        int result = 0;
        RomanToArabic rta = new RomanToArabic();
        if (NumberUtils.isCreatable(input)) {
            result = Integer.parseInt(input);
        } else {
            result = rta.convert(input);
        }
        return result;
    }
}
