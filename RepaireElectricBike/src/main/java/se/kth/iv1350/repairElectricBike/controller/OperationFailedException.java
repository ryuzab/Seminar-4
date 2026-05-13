package se.kth.iv1350.repairElectricBike.controller;

/**
 * Thrown when an operation can not be completed because the program can not access an external system.
 */
public class OperationFailedException extends Exception {
    public OperationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}