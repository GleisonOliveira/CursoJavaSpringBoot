package com.gleisonoliveira.restapi.Controllers;

import org.springframework.web.bind.annotation.RestController;
import com.gleisonoliveira.restapi.Services.MathService.MathServiceInterface;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class MathController {

    @Autowired
    @Resource(name = "mathService")
    MathServiceInterface mathService;

    @RequestMapping(value = "/sum/{number1}/{number2}", method = RequestMethod.GET)
    public Double sum(
            @PathVariable(value = "number1") String number1,
            @PathVariable(value = "number2") String number2) throws Exception {
        return mathService.sum(number1, number2);
    }

    @RequestMapping(value = "/subtract/{number1}/{number2}", method = RequestMethod.GET)
    public Double subtract(
            @PathVariable(value = "number1") String number1,
            @PathVariable(value = "number2") String number2) throws Exception {
        return mathService.subtract(number1, number2);
    }

    @RequestMapping(value = "/multiply/{number1}/{number2}", method = RequestMethod.GET)
    public Double multiply(
            @PathVariable(value = "number1") String number1,
            @PathVariable(value = "number2") String number2) throws Exception {
        return mathService.multiply(number1, number2);
    }

    @RequestMapping(value = "/divide/{number1}/{number2}", method = RequestMethod.GET)
    public Double divide(
            @PathVariable(value = "number1") String number1,
            @PathVariable(value = "number2") String number2) throws Exception {
        return mathService.divide(number1, number2);
    }

    @RequestMapping(value = "/sqrt/{number}", method = RequestMethod.GET)
    public Double sqrt(@PathVariable(value = "number") String number) throws Exception {
        return mathService.sqrt(number);
    }
}
