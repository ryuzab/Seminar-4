package se.kth.iv1350.repairElectricBike.dto;

/**
 * Data transfer object containing customer and bike data that may be shown outside the model.
 */
public class CustomerData {
    private final String name;
    private final String phoneNumber;
    private final String emailAddress;
    private final String brand;
    private final String model;
    private final String serialNumber;

    /**
     * Creates customer data.
     *
     * @param name Customer name.
     * @param phoneNumber Customer phone number.
     * @param emailAddress Customer email address.
     * @param brand Bike brand.
     * @param model Bike model.
     * @param serialNumber Bike serial number.
     */
    public CustomerData(String name, String phoneNumber, String emailAddress,
                        String brand, String model, String serialNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
    }

    /** @return Customer name. */
    public String getName() { return name; }

    /** @return Customer phone number. */
    public String getPhoneNumber() { return phoneNumber; }

    /** @return Customer email address. */
    public String getEmailAddress() { return emailAddress; }

    /** @return Bike brand. */
    public String getBrand() { return brand; }

    /** @return Bike model. */
    public String getModel() { return model; }

    /** @return Bike serial number. */
    public String getSerialNumber() { return serialNumber; }

    /** @return Printable customer data. */
    @Override
    public String toString() {
        return name + ", " + phoneNumber + ", " + emailAddress + ", bike: "
                + brand + " " + model + " (" + serialNumber + ")";
    }
}
