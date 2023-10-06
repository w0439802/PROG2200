package org.example;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        HashMap<String, String> cars = new HashMap<String, String>();

        cars.put("Model","Toyota Corolla");
        cars.put("Year","2004");
        cars.put("Colour","Silver");
        cars.put("Price", "2,500");

        System.out.println(cars);


    }
}