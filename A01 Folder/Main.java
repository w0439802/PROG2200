package org.example;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        HashMap<Integer, Student> studentHashMap = new HashMap<>();

        Student stu1 = new Student(101, "Gerald");
        Student stu2 = new Student(102, "Sally");
        Student stu3 = new Student(103, "Pedro");

        studentHashMap.put(stu1.getStudentId(),stu1);
        studentHashMap.put(stu1.getStudentId(),stu2);
        studentHashMap.put(stu1.getStudentId(),stu3);

        System.out.println("\nAll Students");
        for (Student student : studentHashMap.values()) {
            System.out.println(student);
        }

        int studentIdTooFine = 104;
        Student foundStudent = studentHashMap.get(studentIdTooFine);

        if (foundStudent != null) {
            System.out.println("Found Student: " + foundStudent);

        } else {
            System.out.println("Student with Id " + studentIdTooFine + " not found.");
        }

    }
}