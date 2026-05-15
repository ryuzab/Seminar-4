package se.kth.iv1350.repairElectricBike.integration;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.repairElectricBike.model.Customer;

/**
 * Tests CustomerRegistry.
 */
public class CustomerRegistryTest {
    private CustomerRegistry registry;

    @BeforeEach
    public void setUp() {
        registry = CustomerRegistry.getInstance();
    }

    @AfterEach
    public void tearDown() {
        registry = null;
    }

    @Test
    public void testFindCustomerPhoneSuccess() throws Exception {
        Customer customer = registry.findCustomerPhone("0701234567");
        
        assertNotNull(customer, "Customer should be found.");
        assertEquals("John Pork", customer.getName(), "Wrong customer returned.");
    }

    @Test
    public void testFindCustomerPhoneNotFound() {

        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () -> {
            registry.findCustomerPhone("999999999"); 
        }, "Searching for a non-existent customer should throw CustomerNotFoundException.");        
        assertNotNull(exception.getMessage(), "The exception should have a descriptive message.");
    }
    
}