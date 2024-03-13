package com.gleisonoliveira.restapi.Services.MathService;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gleisonoliveira.restapi.Exceptions.UnsupportedMathOperation;
import com.gleisonoliveira.restapi.Services.Converters.NumberConverter.NumberConverterInterface;
import jakarta.annotation.Resource;

@Service
public class MathService implements MathServiceInterface {
    @Autowired
    @Resource(name = "doubleConverter")
    private NumberConverterInterface<Double> numberConverter;

    public Double sum(String number1, String number2) throws Exception {
        numberConverter.validateNumbers(number1, number2);

        return numberConverter.convert(number1) + numberConverter.convert(number2);
    }

    public Double subtract(String number1, String number2) throws Exception {
        numberConverter.validateNumbers(number1, number2);

        return numberConverter.convert(number1) - numberConverter.convert(number2);
    }

    public Double multiply(String number1, String number2) throws Exception {
        numberConverter.validateNumbers(number1, number2);

        return numberConverter.convert(number1) * numberConverter.convert(number2);
    }

    public Double divide(String number1, String number2) throws Exception {
        numberConverter.validateNumbers(number1, number2);

        Double divisor = numberConverter.convert(number2);

        if (divisor == 0) {
            throw new UnsupportedMathOperation("Incorrect numbers", Arrays.asList("You can`t divide by 0"));
        }

        return numberConverter.convert(number1) / numberConverter.convert(number2);
    }

    public Double sqrt(String number) throws Exception {
        numberConverter.validateNumbers(number);

        return Math.sqrt(numberConverter.convert(number));
    }
}
