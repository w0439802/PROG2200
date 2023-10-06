package org.example;

import java.util.*;

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
            WHITE,
        }



        Random rand = new Random();

        HashMap<Object, Car> cars = new HashMap<Object, Car>();

        //String shapeSize = String.valueOf(rand.nextInt(shape));
        //String theColor = String.valueOf(rand.nextInt(color));
        //String ranPosition = String.valueOf(rand.nextInt(50));
        //String ranSize = String.valueOf(rand.nextInt(25));

        int shape = rand.nextInt(3);
        int color = rand.nextInt(3);
        int position = rand.nextInt(50);
        int size = rand.nextInt(25);
        Car car = new Car(shape, color, position, size) {

        };
    }


        SortedSet<String> Sys = new TreeSet<>(Arrays.toString());




    }