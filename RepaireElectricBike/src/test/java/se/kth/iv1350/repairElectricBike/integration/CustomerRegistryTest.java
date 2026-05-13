package se.kth.iv1350.repairElectricBike.integration;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.repairElectricBike.model.Customer;

/**
 * Tests CustomerRegistry.
 */
public class CustomerRegistryTest {
    private CustomerRegistry registry;

    /**
     * Sets up a fresh registry before each test.
     */
    @BeforeEach
    public void setUp() {
        // Creates a fresh registry before every test
        registry = new CustomerRegistry();
    }

    /**
     * Cleans up the environment after each test.
     */
    @AfterEach
    public void tearDown() {
        // Wipes the registry after every test
        registry = null;
    }

    /**
     * Tests finding a customer by phone number.
     * First method assertNotNull checks its emptiness,
     * Second method assertEquals checks the customers name.
     */
    @Test
    public void testFindCustomerPhoneSuccess() {
        Customer customer = registry.findCustomerPhone("0701234567");
        
        assertNotNull(customer, "Customer should be found.");
        assertEquals("John Pork", customer.getName(), "Wrong customer returned.");
    }

    /**
     * Tests that an unknown phone number returns null.
     */
    @Test
    public void testFindCustomerPhoneNotFound() {
        Customer customer = registry.findCustomerPhone("999999999");
        
        assertNull(customer, "Non-existent phone number should return null.");
    }
}