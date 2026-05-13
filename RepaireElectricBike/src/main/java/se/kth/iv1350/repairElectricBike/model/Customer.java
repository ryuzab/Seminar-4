package se.kth.iv1350.repairElectricBike.model;

/**
 * Represents a customer in the model layer.
 */
public class Customer {
    private final String name;
    private final String phoneNumber;
    private final String emailAddress;
    private final Bike bike;

    /**
     * Creates a customer.
     *
     * @param name Customer name.
     * @param phoneNumber Customer phone number.
     * @param emailAddress Customer email address.
     * @param bike Customer bike.
     */
    public Customer(String name, String phoneNumber, String emailAddress, Bike bike) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.bike = bike;
    }

    /** @return Customer name. */
    public String getName() { return name; }

    /** @return Customer phone number. */
    public String getPhoneNumber() { return phoneNumber; }

    /** @return Customer email address. */
    public String getEmailAddress() { return emailAddress; }

    /** @return Customer bike. */
    public Bike getBike() { return bike; }
}
