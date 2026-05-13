package se.kth.iv1350.repairElectricBike.integration;

/** Thrown when no customer exists for the searched phone number. */
public class CustomerNotFoundException extends Exception {
    private final String phoneNumber;

    /**
     * Creates an exception describing the missing phone number.
     *
     * @param phoneNumber The phone number that was not found.
     */
    public CustomerNotFoundException(String phoneNumber) {
        super("No customer found with phone number: " + phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    /** @return The phone number that was not found. */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
