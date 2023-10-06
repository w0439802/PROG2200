package org.example;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {


        String [] names = {"James", "Will", "Simon", "Jeff", "Liam", "Don", "Zach", "Travis", "Laura", "Sam"};
        SortedSet<String> Sys = new TreeSet<>(Arrays.asList(names));

        System.out.println(Sys);

    }
}