package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class Calculator {
    private Double num1;
    private Double num2;
    private String operator;

    public Logger logger;

    // getter and setter
    public Double getNum1() {
        return num1;
    }

    public void setNum1(Double n) {
        try {
            this.num1 = n;
        } catch (NullPointerException e) {
            this.num1 = 0d;
        }
    }

    public Calculator() {
        logger = new Logger();
        logger.setLogLevel(LogLevel.TRACE);
    }

    public Double getNum2() {
        return this.num2;
    }

    public void setNum2(Double n) {
        this.num2 = n;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }


    // Add method
    public Double add() {
        try {
            logger.log(LogLevel.INFO, num1 + " + " + num2 + " = " + num2 + num1);
            return num1 + num2;
        } catch (NullPointerException e) {
            return 0d;
        }
    }

    // Subtract method
    public Double subtract() {
        try {
            logger.log(LogLevel.INFO, num1 + " - " + num2 + " = " + num2 + num1);
            return num1 - num2;
        } catch (NullPointerException e) {
            return 0d;
        }
    }

    // Multiply method
    public Double multiply() {
        try {
            logger.log(LogLevel.INFO, num1 + " * " + num2 + " = " + num2 + num1);
            return num1 * num2;
        } catch (NullPointerException e) {
            return 0d;
        }

    }

    // Divide method
    public Double divide() {
        try {
            logger.log(LogLevel.INFO, num1 + " / " + num2 + " = " + num2 + num1);
            return num1 / num2;
        } catch (ArithmeticException e) {
            logger.log(LogLevel.ERROR, "Cannot divide by 0 :(");
            System.out.println(e.toString());
            return 0d;

        } catch (Exception e) {
            System.out.println(e.toString());
            return 0d;
        }
    }


    // Perform Calculation
    public Double performCalculation() {
        switch (operator) {
            case "+":
                return add();
            case "-":
                return subtract();
            case "X":
                return multiply();
            case "/":
                try {
                    return divide();
                } catch (Exception e) {
                    System.out.println(e);
                }
            case "clear":

            default:
                return num2;
        }
    }
}
