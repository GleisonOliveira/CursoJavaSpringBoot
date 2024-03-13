package com.gleisonoliveira.restapi.Services.Converters.NumberConverter;

import com.gleisonoliveira.restapi.Exceptions.UnsupportedMathOperation;

public interface NumberConverterInterface<T> {
    /**
     * Validate if the number match
     * 
     * @param numbers
     * @throws UnsupportedMathOperation
     */
    public void validateNumbers(String... numbers) throws UnsupportedMathOperation;

    /**
     * Convert the number
     * 
     * @param number
     * @return
     */
    public T convert(String number);
}
