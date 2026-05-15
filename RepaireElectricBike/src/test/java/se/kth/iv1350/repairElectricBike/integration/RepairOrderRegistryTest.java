package se.kth.iv1350.repairElectricBike.integration;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.repairElectricBike.model.Bike;
import se.kth.iv1350.repairElectricBike.model.Customer;
import se.kth.iv1350.repairElectricBike.model.RepairOrder;
import se.kth.iv1350.repairElectricBike.model.RepairOrderState;

/**
 * Tests RepairOrderRegistry.
 */
public class RepairOrderRegistryTest {
    private RepairOrderRegistry registry;
    private Customer customer;

    @BeforeEach
    public void setUp() {

        registry = RepairOrderRegistry.getInstance();
        customer = new Customer("Anna", "070", "a@b.se", new Bike("A", "B", "C"));
    }

    @AfterEach
    public void tearDown() {
        registry = null;
        customer = null;
    }

    @Test
    public void testCreateAndAddOrder() {
        int id = registry.createAndAddOrder(customer, customer.getBike(), "Broken brakes");
        RepairOrder foundOrder = registry.findOrder(id);
        
        assertNotNull(foundOrder, "Order should be created and found.");
        assertEquals("Broken brakes", foundOrder.getProblemDescription(), "Problem description should match.");
    }

    @Test
    public void testFindMissingOrderReturnsNull() {
        assertNull(registry.findOrder(999), "Unknown id should return null.");
    }

    @Test
    public void testDefensiveCopyingPreventsMutation() {
        int id = registry.createAndAddOrder(customer, customer.getBike(), "Flat tire");
        
        RepairOrder fetchedOrder = registry.findOrder(id);
        fetchedOrder.changeState(RepairOrderState.REJECTED);
        
        RepairOrder fetchedAgain = registry.findOrder(id);
        assertEquals(RepairOrderState.NEWLY_CREATED, fetchedAgain.getState(), "Registry internal state mutated without calling updateOrder!");
    }
    
    @Test
    public void testUpdateOrder() {
        int id = registry.createAndAddOrder(customer, customer.getBike(), "Flat tire");
        
        RepairOrder fetchedOrder = registry.findOrder(id);
        fetchedOrder.changeState(RepairOrderState.REJECTED);
        
        registry.updateOrder(fetchedOrder); 
        
        RepairOrder fetchedAgain = registry.findOrder(id);
        assertEquals(RepairOrderState.REJECTED, fetchedAgain.getState(), "Order state was not updated in the registry.");
    }
}