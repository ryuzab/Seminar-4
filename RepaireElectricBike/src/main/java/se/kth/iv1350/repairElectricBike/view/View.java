package se.kth.iv1350.repairElectricBike.view;

import se.kth.iv1350.repairElectricBike.controller.Controller;
import se.kth.iv1350.repairElectricBike.dto.CustomerData;
import se.kth.iv1350.repairElectricBike.dto.SummaryDTO;
import se.kth.iv1350.repairElectricBike.integration.CustomerNotFoundException;
import se.kth.iv1350.repairElectricBike.integration.CustomerRegistryException;
import se.kth.iv1350.repairElectricBike.integration.FileLogger;

/**
 * Hard-coded view used instead of a real user interface in this seminar solution.
 */
public class View {
    private final Controller controller;
    private final FileLogger errorLogger;

    /**
     * Creates a view.
     *
     * @param controller Controller called by this view.
     */
    public View(Controller controller) {
        this.controller = controller;
        this.errorLogger = new FileLogger("error-log.txt"); // Setup developer logger
    }

    /**
     * Runs the basic flow with hard-coded calls to the controller.
     */
    public void runBasicFlow() {
    
        controller.addRepairOrderObserver(new RepairOrderView());
        
        controller.addRepairOrderObserver(new RepairOrderLogger("repair-order-log.txt"));

        System.out.println("Date: " + controller.getCurrentDate());

        try {
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
                controller.addDiagnostic(orderId, "Battery and its connector are damaged.");
                
                controller.addTasks(orderId, new String[] {"Replace connector", "Replace Battery"});
            } else {
                System.out.println("Repair order not found.");
            }
            
            // RECEPTIONIST
            SummaryDTO summary = controller.getRepairSummary(orderId);
            showRepairOrderSummary(summary);

            controller.acceptRepair(orderId);

        } catch (CustomerNotFoundException e) {
            System.out.println("\n*** USER MESSAGE ***");
            System.out.println("Please double check the phone number. " + e.getMessage());
            System.out.println("********************\n");
            errorLogger.logText("Developer Log: " + e.getMessage());
            
        } catch (CustomerRegistryException e) {
            System.out.println("\n*** USER MESSAGE ***");
            System.out.println("The system database is currently down. Please try again later.");
            System.out.println("********************\n");
            

            errorLogger.logText("CRITICAL DB ERROR: " + e.getMessage());
            
        } catch (Exception e) {
            System.out.println("\n*** USER MESSAGE ***");
            System.out.println("An unexpected error occurred: " + e.getMessage());
            System.out.println("********************\n");
            errorLogger.logText("UNEXPECTED ERROR: " + e.getMessage());
        }
    }

    /**
     * Shows a repair order summary.
     *
     * @param summaryDTO Repair summary to show.
     */
    public void showRepairOrderSummary(SummaryDTO summaryDTO) {
        if (summaryDTO == null) return;
        System.out.println("\n--- REPAIR SUMMARY ---");
        System.out.println(summaryDTO);
        System.out.println("----------------------");
    }
}