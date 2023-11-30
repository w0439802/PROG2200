package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {


    Calculator c = new Calculator();

    @Test
    void multiplyZ() {
        c.setNum1(3.0);
        c.setNum2(0.0);
        double result = c.multiply();
        double expctResult = 0.0;
        assertEquals(expctResult, result, 0.01);

        c.setNum1(6.0);
        c.setNum2(2.0);
        result = c.multiply();
        expctResult = 12.0;
        assertEquals(expctResult, result, 0.01);

        c.setNum1(-24.0);
        c.setNum2(3.0);
        result = c.multiply();
        expctResult = -72.0;
        assertEquals(expctResult, result, 0.01);
    }
    @Test
    void divideZ() {
        c.setNum1(4.0);
        c.setNum2(1.0);
        double result = c.divide();
        double expctResult = 4.0;
        assertEquals(expctResult, result, 0.01);

        c.setNum1(6.0);
        c.setNum2(2.0);
        result = c.divide();
        expctResult = 3.0;
        assertEquals(expctResult, result, 0.01);

        c.setNum1(7.0);
        c.setNum2(2.0);
        result = c.divide();
        expctResult = 3.5;
        assertEquals(expctResult, result, 0.01);
    }
    @Test
    void subtract() {
        c.setNum1(3.0);
        c.setNum2(0.5);
        double result = c.subtract();
        double expctResult = 2.5;
        assertEquals(expctResult, result, 0.01);

        c.setNum1(13.0);
        c.setNum2(2.0);
        result = c.subtract();
        expctResult = 11.0;
        assertEquals(expctResult, result, 0.01);

        c.setNum1(1.0);
        c.setNum2(-0.5);
        result = c.subtract();
        expctResult = 1.5;
        assertEquals(expctResult, result, 0.01);
    }
    @Test
    void add() {
        c.setNum1(2.0);
        c.setNum2(4.0);
        double result = c.add();
        double expctResult = 6.0;
        assertEquals(expctResult, result, 0.01);

        c.setNum1(-2.0);
        c.setNum2(4.0);
        result = c.add();
        expctResult = 2.0;
        assertEquals(expctResult, result, 0.01);

        c.setNum1(800.3);
        c.setNum2(26.0);
        result = c.add();
        expctResult = 826.3;
        assertEquals(expctResult, result, 0.01);
    }

    @Test
    void operator() {
        c.setNum1(12.0);
        c.setNum2(6.0);
        c.setOperator("-");
        double result = c.performCalculation();
        double expctResult = 6.0;
        assertEquals(expctResult, result, 0.01);

        c.setNum1(8.0);
        c.setNum2(6.0);
        c.setOperator("X");
        result = c.performCalculation();
        expctResult = 48.0;
        assertEquals(expctResult, result, 0.01);
    }


}
