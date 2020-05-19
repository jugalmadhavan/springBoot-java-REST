package com.example.arithmetic.service.impl;

import com.example.arithmetic.controller.objects.CalculationInput;
import com.example.arithmetic.repository.CalculationRepository;
import com.example.arithmetic.service.CalculationService;
import com.example.arithmetic.service.impl.helpers.ArabicToRoman;
import com.example.arithmetic.utils.ConvertToInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static com.example.arithmetic.service.impl.helpers.ArabicToRoman.*;

@Service
@Transactional
public class CalculationServiceImpl implements CalculationService {

    Logger logger = LoggerFactory.getLogger(CalculationServiceImpl.class);

    private final CalculationRepository calculationRepository;
    static final List<String> RomanValueErrors = Arrays.asList(ZERO, OVERFLOW, NEGATIVE);

    @Autowired
    CalculationServiceImpl(CalculationRepository calculationRepository) {
        this.calculationRepository = calculationRepository;
    }

    @Override
    public com.example.arithmetic.data.Book performCalculation(CalculationInput calculationInput) {

        ConvertToInt cti = new ConvertToInt();
        ArabicToRoman atr = new ArabicToRoman();
        com.example.arithmetic.data.Book operationResult;

        int firstNumber = cti.convert(calculationInput.getFirst());
        int secondNumber = cti.convert(calculationInput.getSecond());

        int resultDecimal = getResultDecimal(calculationInput, firstNumber, secondNumber);
        String resultRoman = atr.convert(resultDecimal);

        if (RomanValueErrors.contains(resultRoman)) {
            operationResult = transactionOperationResultError(calculationInput, resultRoman, resultDecimal);
        } else {
            operationResult = transactionOperationResultSuccess(calculationInput, resultDecimal, resultRoman);
        }

        return operationResult;
    }

    private int getResultDecimal(CalculationInput calculationInput, int firstNumber, int secondNumber) {
        int resultDecimal = 0;
        switch (calculationInput.getOperator()) {
            case ADD:
                resultDecimal = firstNumber + secondNumber;
                break;
            case SUBTRACT:
                resultDecimal = firstNumber - secondNumber;
                break;
            case MULTIPLY:
                resultDecimal = firstNumber * secondNumber;
                break;
            case DIVIDE:
                resultDecimal = firstNumber / secondNumber;
                break;
        }
        return resultDecimal;
    }

    @Override
    public com.example.arithmetic.data.Book transactionOperationResultError(CalculationInput calculationInput, String errorMessage) {
        return transactionOperationResult(calculationInput, null, null, 0, errorMessage);
    }

    public com.example.arithmetic.data.Book transactionOperationResultError(CalculationInput calculationInput, String errorMessage, Integer resultDecimal) {
        return transactionOperationResult(calculationInput, resultDecimal, null, 0, errorMessage);
    }

    private com.example.arithmetic.data.Book transactionOperationResultSuccess(CalculationInput calculationInput, Integer resultDecimal, String resultRoman) {
        return transactionOperationResult(calculationInput, resultDecimal, resultRoman, 1, null);
    }

    private com.example.arithmetic.data.Book transactionOperationResult(CalculationInput calculationInput, Integer resultDecimal, String resultRoman, int isSuccess, String errorMessage) {

        com.example.arithmetic.data.Book operationResult = new com.example.arithmetic.data.Book(0L,
                calculationInput.getOperator(),
                calculationInput.getFirst(),
                calculationInput.getSecond(),
                resultRoman,
                resultDecimal,
                isSuccess,
                errorMessage);

        return calculationRepository.save(operationResult);
    }
}
