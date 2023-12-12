package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @usage Calculator class used for all logical
 * side of the program. Deals with calculation
 * checking values and other.
 */
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

    /**
     * @usage Constructor to call our logger to
     * track the information
     */
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
    /**
     * @usage calculation to calculate the total
     * when adding two numbers selected
     * @return
     */
    public Double add() {
        try {
            logger.log(LogLevel.INFO, num1 + " + " + num2 + " = " + num2 + num1);
            return num1 + num2;
        } catch (NullPointerException e) {
            return 0d;
        }
    }

    // Subtract method

    /**
     * @usage calculation to calculate the total
     * when subtracting two numbers selected
     * @return
     */
    public Double subtract() {
        try {
            logger.log(LogLevel.INFO, num1 + " - " + num2 + " = " + num2 + num1);
            return num1 - num2;
        } catch (NullPointerException e) {
            return 0d;
        }
    }

    // Multiply method
    /**
     * @usage calculation to calculate the total
     * when multiplying two numbers selected
     * @return
     */
    public Double multiply() {
        try {
            logger.log(LogLevel.INFO, num1 + " * " + num2 + " = " + num2 + num1);
            return num1 * num2;
        } catch (NullPointerException e) {
            return 0d;
        }

    }

    // Divide method
    /**
     * @usage calculation to calculate the total
     * when dividing two numbers selected, it will also
     * check to see if the 2nd number is possible to
     * divide from
     * @return
     */
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

    /**
     * @usage Will check to see what the users operator
     * is and will preform the necessairy calculation
     * @return
     */
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
