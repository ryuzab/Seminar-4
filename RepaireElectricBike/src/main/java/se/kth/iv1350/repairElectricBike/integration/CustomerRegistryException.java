package se.kth.iv1350.repairElectricBike.integration;

/** Thrown when the customer registry database can not be called. */
public class CustomerRegistryException extends Exception {
    /**
     * Creates an exception with a message.
     *
     * @param message Information about the database failure.
     */
    public CustomerRegistryException(String message) {
        super(message);
    }

    /**
     * Creates an exception with a message and cause.
     *
     * @param message Information about the database failure.
     * @param cause The underlying cause.
     */
    public CustomerRegistryException(String message, Throwable cause) {
        super(message, cause);
    }
}
