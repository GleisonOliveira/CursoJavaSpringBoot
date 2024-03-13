package com.gleisonoliveira.restapi.Services.MathService;

public interface MathServiceInterface {
    /**
     * Sum the 2 numbers
     * @param number1
     * @param number2
     * @return
     * @throws Exception
     */
    public Double sum(String number1, String number2) throws Exception;

    /**
     * Subtract the 2 numbers
     * @param number1
     * @param number2
     * @return
     * @throws Exception
     */
    public Double subtract(String number1, String number2) throws Exception;

    /**
     * Multiply the 2 numbers
     * @param number1
     * @param number2
     * @return
     * @throws Exception
     */
    public Double multiply(String number1, String number2) throws Exception;

    /**
     * Divide the 2 numbers
     * @param number1
     * @param number2
     * @return
     * @throws Exception
     */
    public Double divide(String number1, String number2) throws Exception;

    /**
     * Return the square root of number
     * @param number
     * @return
     * @throws Exception
     */
    public Double sqrt(String number) throws Exception;
}
