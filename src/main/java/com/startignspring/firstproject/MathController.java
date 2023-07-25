package com.startignspring.firstproject;

import com.startignspring.firstproject.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;
@RestController
public class MathController {
    private final AtomicLong counter = new AtomicLong();
    @RequestMapping(value = "/sum/{num1}/{num2}", method = RequestMethod.GET)
    public Double sum(
            @PathVariable(value = "num1") String num1,
            @PathVariable(value = "num2") String num2) throws Exception {
        
        if(!num1.matches("[0-9]+") || !num2.matches("[0-9]+"))
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        
        return convertToDouble(num1) + convertToDouble(num2);
    }
    @RequestMapping(value = "/sub/{num1}/{num2}", method = RequestMethod.GET)
    public Double sub(
            @PathVariable(value = "num1") String num1,
            @PathVariable(value = "num2") String num2) throws Exception {

        if(!num1.matches("[0-9]+") || !num2.matches("[0-9]+"))
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        return convertToDouble(num1) - convertToDouble(num2);
    }
    @RequestMapping(value = "/mult/{num1}/{num2}", method = RequestMethod.GET)
    public Double mult(
            @PathVariable(value = "num1") String num1,
            @PathVariable(value = "num2") String num2) throws Exception {

        if(!num1.matches("[0-9]+") || !num2.matches("[0-9]+"))
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        return convertToDouble(num1) * convertToDouble(num2);
    }
    @RequestMapping(value = "/split/{num1}/{num2}", method = RequestMethod.GET)
    public Double split(
            @PathVariable(value = "num1") String num1,
            @PathVariable(value = "num2") String num2) throws Exception {

        if(!num1.matches("[0-9]+") || !num2.matches("[0-9]+"))
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        return convertToDouble(num1) / convertToDouble(num2);
    }

    @RequestMapping(value = "/average/{num1}/{num2}", method = RequestMethod.GET)
    public Double average(
            @PathVariable(value = "num1") String num1,
            @PathVariable(value = "num2") String num2) throws Exception {

        if(!num1.matches("[0-9]+") || !num2.matches("[0-9]+"))
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        return (convertToDouble(num1) + convertToDouble(num2))/2;
    }

    @RequestMapping(value = "/sqrt/{num1}", method = RequestMethod.GET)
    public Double sqrt(@PathVariable(value = "num1") String num1) throws Exception {

        if(!num1.matches("[0-9]+"))
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        return Math.sqrt(convertToDouble(num1));
    }

    private Double convertToDouble(String strNumber) {
        if(strNumber == null) return 0D;
        String number = strNumber.replaceAll(",", ".");
        if(isNumeric(number)) return Double.parseDouble(number);
        return 0D;
    }

    private boolean isNumeric(String number) {
        if(number == null) return false;
        String number2 = number.replaceAll(",", ".");
        return number2.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}

