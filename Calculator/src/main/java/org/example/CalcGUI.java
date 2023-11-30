package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcGUI {
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a6Button;
    private JButton a9Button;
    private JButton a5Button;
    private JButton a8Button;
    private JButton a4Button;
    private JButton a7Button;
    private JTextField num2TextField;
    private JButton a0Button;
    private JButton divideButton;
    private JButton multiplyButton;
    private JButton addButton;
    private JButton subtractButton;
    private JButton period;
    private JButton equals;
    private JButton clear;
    private JButton backspace;
    private JTextField num1Textfield;
    private JFrame mainFrame;
    private JPanel panelMain;

    private Calculator calc;

    public void setCalculator(Calculator c) {
        this.calc = c;
    }


    public CalcGUI() {
        mainFrame = new JFrame("Calculator");
        mainFrame.setSize(400,400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setContentPane(panelMain);
        mainFrame.setVisible(true);

        ActionListener numListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JButton numButton = (JButton) e.getSource();
                    num2TextField.setText(num2TextField.getText() + numButton.getText());
                } catch (Exception exception) {
                    System.out.println(exception.toString());
                }
            }
        };
        ActionListener operatorListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get value from textfield and store in calculator num1
                    Double currentNumber = Double.parseDouble(num2TextField.getText());
                    calc.setNum1(currentNumber);

                    // Set calc operator based on button clicked
                    JButton opButton = (JButton) e.getSource();
                    //System.out.println("Mellon " + opButton);
                    String buttonText = opButton.getText();
                    calc.setOperator(buttonText);

                    // Clear text field for next number
                    num2TextField.setText("");

                    // Setting extra text field to operator string for fun
                    num1Textfield.setText(buttonText);
                } catch (Exception exception) {
                    System.out.println(exception.toString());
                }
            }
        };

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                num1Textfield.setText("");
                num2TextField.setText("");
            }
        });
        a0Button.addActionListener(numListener);
        a1Button.addActionListener(numListener);
        a2Button.addActionListener(numListener);
        a3Button.addActionListener(numListener);
        a4Button.addActionListener(numListener);
        a5Button.addActionListener(numListener);
        a6Button.addActionListener(numListener);
        a7Button.addActionListener(numListener);
        a8Button.addActionListener(numListener);
        a9Button.addActionListener(numListener);

        multiplyButton.addActionListener(operatorListener);
        subtractButton.addActionListener(operatorListener);
        addButton.addActionListener(operatorListener);
        divideButton.addActionListener(operatorListener);

        equals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double num2 = Double.parseDouble(num2TextField.getText());
                calc.setNum2(num2);
                Double result = calc.performCalculation();
                num2TextField.setText(result.toString());
                num1Textfield.setText(result.toString());
            }
        });
        backspace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (num2TextField.getText().length() <= 1) {
                    num2TextField.setText("0");
                } else {
                    num2TextField.setText(num2TextField.getText().substring(0, num2TextField.getText().length() - 1));
                }

            }
        });
        period.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                num2TextField.setText(num2TextField.getText() + ".");
            }
        });
    }
}


