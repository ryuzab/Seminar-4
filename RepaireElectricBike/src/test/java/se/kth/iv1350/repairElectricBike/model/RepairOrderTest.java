package se.kth.iv1350.repairElectricBike.model;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests RepairOrder.
 */
public class RepairOrderTest {
    private Customer customer;
    private RepairOrder order;

    @BeforeEach
    public void setUp() {
        customer = new Customer("Tung", "070", "a@b.se", new Bike("A", "B", "C"));
        order = new RepairOrder(1, customer, customer.getBike(), "Problem");
    }

    @AfterEach
    public void tearDown() {
        customer = null;
        order = null;
    }

    @Test
    public void testCalculateTotalCost() {
        order.addTasks(List.of(new RepairTask("Task 1", 300), new RepairTask("Task 2", 300)));
        assertEquals(600, order.calculateTotalCost(), "calculateTotalCost gave incorrect answer.");
    }

    @Test
    public void testCalculateTotalCostNoTasks() {
        assertEquals(0, order.calculateTotalCost(), "calculateTotalCost should be 0 when no tasks exist.");
    }

    @Test
    public void testAddTasksChangesState() {
        order.addTasks(List.of(new RepairTask("Task", 300)));
        assertEquals(RepairOrderState.READY_FOR_APPROVAL, order.getState(), "States not properly changed.");
    }

    @Test
    public void testCopyConstructor() {
        order.addTasks(List.of(new RepairTask("Task 1", 300)));
        RepairOrder copy = new RepairOrder(order);

        assertEquals(order.getId(), copy.getId(), "IDs should match.");
        assertEquals(order.getProblemDescription(), copy.getProblemDescription(), "Descriptions should match.");
        assertEquals(order.getState(), copy.getState(), "States should match.");
        

        assertNotSame(order, copy, "Objects should not be the same reference.");
        
        order.changeState(RepairOrderState.REJECTED);
        assertEquals(RepairOrderState.READY_FOR_APPROVAL, copy.getState(), "Copy state should not change when original changes.");
    }
}