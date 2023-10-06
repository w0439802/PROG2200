package org.example;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        enum shape {
            LARGE,
            MEDIUM,
            SMALL
        }
        enum color {
            RED,
            BLUE,
            GREEN,
            PINK,
            SILVER,
            WHITE,
            BLACK,
            YELLOW
        }

        HashMap<String, String> cars = new HashMap<String, String>();

        cars.put("Shape", shape.values());
        cars.put("Size", "");
        cars.put("Colour", color.values());
        cars.put("Position", "");





    }
}