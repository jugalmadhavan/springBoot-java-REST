package com.example.arithmetic.controller.helpers;

import com.example.arithmetic.controller.objects.CalculationInput;
import com.example.arithmetic.service.BookService;
import com.example.arithmetic.service.CalculationService;
import com.example.arithmetic.utils.ConvertToInt;
import com.example.book.controller.objects.CreateBookInput;
import com.example.book.data.Book;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CreateBookAndRespond {

    static final int MIN_VALUE = 1;
    static final int MAX_VALUE = 3999;
    static final String INVALID_VALUE_RANGE = "Entered value is not in range between 1 and 3999";
    static final String INVALID_ROMAN_PATTERN = "Wrong Roman numerals entered";
    static final String ROMAN_PATTERN = "^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";

    Logger logger = LoggerFactory.getLogger(CreateBookAndRespond.class);

    private final BookService bookService;

    public CreateBookAndRespond(BookService bookService) {
        this.bookService = bookService;
    }

    public ResponseEntity<Book> getOperationResultResponseEntity(CreateBookInput createBookInput) {

        ResponseEntity responseEntity;

        if (!isValidFirstRomanValuePattern(calculationInput) || !isValidSecondRomanValuePattern(calculationInput)) {
            logger.error(INVALID_ROMAN_PATTERN);

            calculationService.transactionOperationResultError(calculationInput, INVALID_ROMAN_PATTERN);

            responseEntity = new ResponseEntity(INVALID_ROMAN_PATTERN, HttpStatus.BAD_REQUEST);
        } else if (isValidInputValues(calculationInput)) {
            responseEntity = new ResponseEntity(calculationService.performCalculation(calculationInput), HttpStatus.OK);
        } else {
            logger.error(INVALID_VALUE_RANGE);

            calculationService.transactionOperationResultError(calculationInput, INVALID_VALUE_RANGE);

            responseEntity = new ResponseEntity(INVALID_VALUE_RANGE, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    private boolean isValidInputValues(CalculationInput calculationInput) {
        ConvertToInt cti = new ConvertToInt();
        int firstValue = cti.convert(calculationInput.getFirst());
        int secondValue = cti.convert(calculationInput.getSecond());
        boolean isValid = true;

        if (firstValue < MIN_VALUE || firstValue > MAX_VALUE || secondValue < MIN_VALUE || secondValue > MAX_VALUE) {
            isValid = false;
        }
        return isValid;
    }

    private boolean isValidFirstRomanValuePattern(CalculationInput calculationInput) {
        boolean result = true;
        if (!NumberUtils.isCreatable(calculationInput.getFirst())) {
            result = calculationInput.getFirst().matches(ROMAN_PATTERN);
        }
        return result;
    }

    private boolean isValidSecondRomanValuePattern(CalculationInput calculationInput) {
        boolean result = true;
        if (!NumberUtils.isCreatable(calculationInput.getSecond())) {
            result = calculationInput.getSecond().matches(ROMAN_PATTERN);
        }
        return result;
    }
}
