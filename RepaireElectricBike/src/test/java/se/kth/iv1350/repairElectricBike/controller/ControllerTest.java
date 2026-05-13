package se.kth.iv1350.repairElectricBike.controller;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.repairElectricBike.dto.CustomerData;
import se.kth.iv1350.repairElectricBike.dto.SummaryDTO;
import se.kth.iv1350.repairElectricBike.integration.CustomerRegistry;
import se.kth.iv1350.repairElectricBike.integration.Date;
import se.kth.iv1350.repairElectricBike.integration.Printer;
import se.kth.iv1350.repairElectricBike.integration.RepairOrderRegistry;
import se.kth.iv1350.repairElectricBike.model.RepairOrderState;

/**
 * Tests Controller.
 */
public class ControllerTest {
    private Controller controller;
    private RepairOrderRegistry repairOrderRegistry;
    private CustomerRegistry customerRegistry;
    private Date date;
    private Printer printer;

    @BeforeEach
    public void setUp() {
        repairOrderRegistry = new RepairOrderRegistry();
        customerRegistry = new CustomerRegistry();
        date = new Date();
        printer = new Printer();
        controller = new Controller(repairOrderRegistry, customerRegistry, date, printer);
    }

    @AfterEach
    public void tearDown() {
        repairOrderRegistry = null;
        customerRegistry = null;
        date = null;
        printer = null;
        controller = null;
    }

    @Test
    public void testGetCustomerReturnsCustomerData() {
        CustomerData customerData = controller.getCustomer("0707654321");
        assertNotNull(customerData, "Customer data should not be null.");
        assertEquals("Charlie Kirk", customerData.getName(), "Incorrect customer fetched.");
    }

    @Test
    public void testGetRepairSummary() {
        int orderId = controller.startRepairOrder("0707654321", "Problem");
        assertTrue(controller.findRepairOrder(orderId), "Order should be found in registry.");
        
        controller.addDiagnostic(orderId, "Diagnostic");
        controller.addTasks(orderId, new String[] {"Task one", "Task two"});
        
        SummaryDTO summary = controller.getRepairSummary(orderId);
        assertNotNull(summary, "Summary should not be null.");
        assertEquals(orderId, summary.getOrderId(), "Order id not correct.");
        assertEquals(2, summary.getTasks().size(), "Number of tasks not correct.");
    }

    @Test
    public void testRejectRepair() {
        int orderId = controller.startRepairOrder("0701234567", "Broken chain");
        controller.rejectRepair(orderId);
        
        assertEquals(RepairOrderState.REJECTED, repairOrderRegistry.findOrder(orderId).getState(), "Repair order was not successfully rejected.");
    }

    @Test
    public void testAcceptRepair() {
        int orderId = controller.startRepairOrder("0701234567", "Broken motor");
        controller.acceptRepair(orderId);
        
        assertEquals(RepairOrderState.ACCEPTED, repairOrderRegistry.findOrder(orderId).getState(), "Repair order was not successfully accepted.");
    }

    @Test
    public void testInvalidOrderIdReturnsGracefully() {
        assertFalse(controller.findRepairOrder(999), "Non-existent order should return false.");
        assertNull(controller.getRepairSummary(999), "Summary for non-existent order should be null.");
    }
}