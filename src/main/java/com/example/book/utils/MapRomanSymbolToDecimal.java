package com.example.arithmetic.utils;

import java.util.HashMap;
import java.util.Map;

public class MapRomanSymbolToDecimal {

    public Map<Character, Integer> getSymbolMap(){
        Map<Character, Integer> mapSymbol = new HashMap<>();
        mapSymbol.put('C',100);
        mapSymbol.put('D',500);
        mapSymbol.put('I',1);
        mapSymbol.put('L',50);
        mapSymbol.put('M',1000);
        mapSymbol.put('V',5);
        mapSymbol.put('X',10);

        return mapSymbol;
    }

}
