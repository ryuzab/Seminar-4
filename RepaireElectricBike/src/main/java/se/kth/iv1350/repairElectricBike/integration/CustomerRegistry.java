package se.kth.iv1350.repairElectricBike.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.repairElectricBike.model.Bike;
import se.kth.iv1350.repairElectricBike.model.Customer;

/**
 * Stores and finds customers. Replaces a database in this seminar solution.
 */
public class CustomerRegistry {
    private final List<Customer> customers = new ArrayList<>();

    /**
     * Creates a customer registry with sample data.
     */
    public CustomerRegistry() {
        customers.add(new Customer("John Pork", "0701234567", "John@gmail.com",
                new Bike("TheBob", "Bob", "BIKE-1001")));
            // Extra User
        customers.add(new Customer("Charlie Kirk", "0707654321", "Charlie@gmail.com",
                new Bike("Neten", "yahu", "BIKE-2002")));
    }

    /**
     * Finds a customer by phone number.
     *
     * @param phoneNumber Phone number to search for.
     * @return Matching customer, or null if no customer was found.
     */
    public Customer findCustomerPhone(String phoneNumber) {
        for (Customer customer : customers) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return customer;
            }
        }
        return null;
    }
}
