package org.example;

import java.util.*;
//enum lists
enum Shape {
    LARGE,
    MEDIUM,
    SMALL
}
enum Color {
    RED,
    BLUE,
    WHITE,
}
//Main class to set the values of the car
class Car {
    private Shape shape;
    private Color color;
    private int size;
    int x;
    int y;

    //Gets the car values and sets them
    public Car(Shape shape,Color color ,int size , int x, int y) {
        this.shape = shape;
        this.size = size;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    //Truen the values into a String if toString is called.
    @Override
    public String toString() {
        return "Shape: " + shape + ", Size: " + size + ", Color: " + color + ", Position: (" + x + ", " + y + ")";
    }

}

//Main class to run the function
public class Main {
    public static void main(String[] args) {

        //Main "Car" HashMap
        HashMap<Integer, Car> cars = new HashMap<Integer, Car>();

        //The Random function
        Random rand = new Random();


        //This loop is used to generate random values for the position, a random size, and to chose randomly thre the enum lists, it will loop 3 times before finishing
        for (int i = 0; i < 3; i++)
        {
            Shape randShape = Shape.values()[rand.nextInt(Shape.values().length)];
            Color randColor = Color.values()[rand.nextInt(Color.values().length)];
            int randomX = rand.nextInt(50);
            int randomY = rand.nextInt(50);
            int randSize = rand.nextInt(25);
            Car car = new Car(randShape, randColor, randomX, randomY, randSize);
            cars.put(i, car);
        }
        //loop to print out every entry in the "car" HashMap
        for (Map.Entry<Integer, Car> entry : cars.entrySet()) {
            System.out.println("Object " + entry.getKey() + ": " + entry.getValue().toString());
        }
    }

}
