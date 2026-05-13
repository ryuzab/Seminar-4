package se.kth.iv1350.repairElectricBike.model;

/**
 * Represents an electric bike belonging to a customer.
 */
public class Bike {
    private final String brand;
    private final String model;
    private final String serialNumber;

    /**
     * Creates a bike.
     *
     * @param brand Bike brand.
     * @param model Bike model.
     * @param serialNumber Bike serial number.
     */
    public Bike(String brand, String model, String serialNumber) {
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
    }

    /** @return Bike brand. */
    public String getBrand() { return brand; }

    /** @return Bike model. */
    public String getModel() { return model; }

    /** @return Bike serial number. */
    public String getSerialNumber() { return serialNumber; }
}
