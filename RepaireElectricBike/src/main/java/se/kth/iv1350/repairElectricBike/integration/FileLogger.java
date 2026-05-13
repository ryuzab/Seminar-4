package se.kth.iv1350.repairElectricBike.integration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/** Writes log messages to a file. */
public class FileLogger {
    private final String logFileName;

    /**
     * Creates a logger that writes to the specified file.
     *
     * @param logFileName Name of the log file.
     */
    public FileLogger(String logFileName) {
        this.logFileName = logFileName;
    }

    /**
     * Logs an exception and its stack trace.
     *
     * @param exception The exception to log.
     */
    public void logException(Exception exception) {
        try (PrintWriter out = new PrintWriter(new FileWriter(logFileName, true))) {
            out.println(LocalDateTime.now() + " ERROR: " + exception.getMessage());
            exception.printStackTrace(out);
        } catch (IOException logFailure) {
            System.err.println("Could not write to log file: " + logFailure.getMessage());
        }
    }

    /**
     * Logs plain text.
     *
     * @param text The text to log.
     */
    public void logText(String text) {
        try (PrintWriter out = new PrintWriter(new FileWriter(logFileName, true))) {
            out.println(LocalDateTime.now() + " " + text);
        } catch (IOException logFailure) {
            System.err.println("Could not write to log file: " + logFailure.getMessage());
        }
    }
}
