package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        Calculator c = new Calculator();
        CalcGUI calcGUI = new CalcGUI();
        calcGUI.setCalculator(c);

        c.setNum1(12.0);
        c.setNum2(2.0);
        System.out.println(c.add());
        System.out.println(c.subtract());
        System.out.println(c.multiply());
        System.out.println(c.divide());

    }
}