package com.gleisonoliveira.restapi.Services.Converters.NumberConverter;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import com.gleisonoliveira.restapi.Exceptions.UnsupportedMathOperation;

@Service
public class DoubleConverter implements NumberConverterInterface<Double> {
    private static String errorTemplate = "The number '%s' is incorrect format";

    /**
     * Verify if number is numeric
     * 
     * @param number
     * @return
     */
    public boolean isNumeric(String number) {
        if (number == null)
            return false;

        String parsedNumber = replaceComma(number);

        return parsedNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    /**
     * Convert the number to double
     * 
     * @param number
     * @return
     */
    public Double convert(String number) {
        if (number == null)
            return 0D;

        String parsedNumber = replaceComma(number);

        if (isNumeric(parsedNumber))
            return Double.parseDouble(parsedNumber);

        return 0D;
    }

    /**
     * Replace the comma into strings
     * 
     * @param number
     * @return
     */
    private String replaceComma(String number) {
        return number.replaceAll(",", ".");
    }

    /**
     * Validate the numbers from request
     * 
     * @param numbers
     * @throws UnsupportedMathOperation
     */
    public void validateNumbers(String... numbers) throws UnsupportedMathOperation {
        ArrayList<String> errors = new ArrayList<>();

        for (String number : numbers) {
            if (!isNumeric(number)) {
                errors.add(String.format(errorTemplate, number));
            }
        }

        if (errors.size() > 0) {
            throw new UnsupportedMathOperation("Incorrect numbers", errors);
        }
    }
}
