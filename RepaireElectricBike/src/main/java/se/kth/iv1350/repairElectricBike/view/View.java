package se.kth.iv1350.repairElectricBike.view;

import se.kth.iv1350.repairElectricBike.controller.Controller;
import se.kth.iv1350.repairElectricBike.dto.CustomerData;
import se.kth.iv1350.repairElectricBike.dto.SummaryDTO;

/**
 * Hard-coded view used instead of a real user interface in this seminar solution.
 */
public class View {
    private final Controller controller;

    /**
     * Creates a view.
     *
     * @param controller Controller called by this view.
     */
    public View(Controller controller) {
        this.controller = controller;
    }

    /**
     * Runs the basic flow with hard-coded calls to the controller.
     */
    public void runBasicFlow() {
        // SYSTEM
        System.out.println("Date: " + controller.getCurrentDate());

        // RECEPTIONIST
        CustomerData customerData = controller.getCustomer("0701234567");
        if (customerData == null) {
            System.out.println("Customer not found.");
            return;
        }
        System.out.println("Customer data:");
        System.out.println(customerData);
    
        int orderId = controller.startRepairOrder("0701234567", "Battery does not charge.");
        System.out.println("Created repair order id: " + orderId);

        // TECHNICIAN
        boolean found = controller.findRepairOrder(orderId);
        if (found) {
            // Pass orderId to controller methods
            controller.addDiagnostic(orderId, "Battery and it's connector is damaged.");
            controller.addTasks(orderId, new String[] {"Replace connector", "Replace Battery"});
        } else {
            System.out.println("Repair order not found.");
        }
        
        // RECEPTIONIST
        SummaryDTO summary = controller.getRepairSummary(orderId);
        if (summary != null) {
            showRepairOrderSummary(summary);
        }

        controller.acceptRepair(orderId);
    }

    /**
     * Shows a repair order summary.
     *
     * @param summaryDTO Repair summary to show.
     */
    public void showRepairOrderSummary(SummaryDTO summaryDTO) {
        System.out.println("\n--- REPAIR SUMMARY ---");
        System.out.println(summaryDTO);
        System.out.println("----------------------");
    }
}