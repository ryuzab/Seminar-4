package se.kth.iv1350.repairElectricBike.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.repairElectricBike.model.Bike;
import se.kth.iv1350.repairElectricBike.model.Customer;

/**
 * Stores and finds customers. Replaces a database in this seminar solution.
 */
public class CustomerRegistry {
    public static final String DATABASE_FAILURE_PHONE_NUMBER = "0000000000";
    private final List<Customer> customers = new ArrayList<>();

    /**
     * Creates a customer registry with sample data.
     */
    public CustomerRegistry() {
        customers.add(new Customer("John Pork", "0701234567", "John@gmail.com",
                new Bike("TheBob", "Bob", "BIKE-1001")));
        customers.add(new Customer("Charlie Kirk", "0707654321", "Charlie@gmail.com",
                new Bike("Neten", "yahu", "BIKE-2002")));
    }

    /**
     * Finds a customer by phone number.
     *
     * @param phoneNumber Phone number to search for.
     * @return Matching customer.
     * @throws CustomerNotFoundException If no customer has the searched phone number.
     * @throws CustomerRegistryException If the simulated database can not be called.
     */
    public Customer findCustomerPhone(String phoneNumber)
            throws CustomerNotFoundException, CustomerRegistryException {
        if (DATABASE_FAILURE_PHONE_NUMBER.equals(phoneNumber)) {
            throw new CustomerRegistryException(
                    "Could not call customer registry database for phone number: " + phoneNumber);
        }

        for (Customer customer : customers) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return customer;
            }
        }
        throw new CustomerNotFoundException(phoneNumber);
    }
}
