package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * @usage Used to create a tracker in the terminal
 * that will display information for the user to use
 * in case of testing
 */
public class Logger {
    private static Logger _instance;
    private LogLevel loglevel;
    private List<String> logs;
    private PrintWriter fileWriter;

    /**
     * @usage The actual logging part to the entire
     * object / class. Tracks the users calculations
     * adds them to a array and then throws them back
     * to the user in the terminal for display
     */
    public Logger() {
        loglevel = LogLevel.INFO;
        logs = new ArrayList<>();
        try {
            fileWriter = new PrintWriter(new FileWriter("LoggingExample.log"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getInstance() {
        if (_instance == null) {
            synchronized (Logger.class){
                if(_instance == null) {
                    _instance = new Logger();
                }
            }
        }
        return _instance;
    }

    public void setLogLevel(LogLevel level) {
        loglevel = level;
    }

    /**
     * @usage The actual log itself
     * @param level
     * @param message
     */
    public void log(LogLevel level, String message) {
        if(level.ordinal() >= loglevel.ordinal()) {
            String log = String.format("[%s] [%s] %s", level, LocalDateTime.now(), message);
            System.out.println(log);
            fileWriter.println(log);
            fileWriter.flush();
        }
        logs.add(String.format("[%s] [%s] %s", level, LocalDateTime.now(), message));
    }

    public void displayLogs() {
        for (String log: logs) {
            System.out.println(log);
        }
    }
}
