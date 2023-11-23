package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Error World!");
        System.out.println("Hello world!");

        Logger logger = new Logger();
        Logger simplelogger1 = new Logger();
        Logger simplelogger2 = new Logger();
        System.out.println("Are both loggers of the same refrence? " + (simplelogger1 == simplelogger2));


        simplelogger1.setLogLevel(LogLevel.TRACE);
        System.out.println("\nTRACE level defined...");
        simplelogger1.log(LogLevel.TRACE ,"trace level log message");
        simplelogger1.log(LogLevel.DEBUG ,"debug level log message");
        simplelogger1.log(LogLevel.INFO ,"info level log message");
        simplelogger1.log(LogLevel.WARN ,"warn level log message");
        simplelogger1.log(LogLevel.ERROR ,"error level log message");

        System.out.println("Display all logged messages...");
        simplelogger1.displayLogs();
    }
}