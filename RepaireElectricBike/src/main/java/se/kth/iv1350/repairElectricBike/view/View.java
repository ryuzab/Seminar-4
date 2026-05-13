package se.kth.iv1350.repairElectricBike.view;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.repairElectricBike.controller.Controller;
import se.kth.iv1350.repairElectricBike.controller.OperationFailedException;
import se.kth.iv1350.repairElectricBike.dto.CustomerData;
import se.kth.iv1350.repairElectricBike.dto.SummaryDTO;
import se.kth.iv1350.repairElectricBike.integration.CustomerNotFoundException;
import se.kth.iv1350.repairElectricBike.integration.CustomerRegistry;
import se.kth.iv1350.repairElectricBike.integration.FileLogger;
import se.kth.iv1350.repairElectricBike.model.RepairTask;

/**
 * Hard-coded view used instead of a real user interface in this seminar solution.
 */
public class View {
    private final Controller controller;
    private final FileLogger errorLogger = new FileLogger("error-log.txt");

    /**
     * Creates a view.
     *
     * @param controller Controller called by this view.
     */
    public View(Controller controller) {
        this.controller = controller;
        controller.addRepairOrderObserver(new RepairOrderView());
        controller.addRepairOrderObserver(new RepairOrderLogger("repair-order-log.txt"));
    }

    /**
     * Runs the basic flow with hard-coded calls to the controller.
     */
    public void runBasicFlow() {
        System.out.println("Date: " + controller.getCurrentDate());

        try {
            CustomerData customerData = controller.getCustomer("0701234567");
            System.out.println(customerData);

            int orderId = controller.startRepairOrder("0701234567", "Battery does not charge.");

            boolean found = controller.findRepairOrder(orderId);
            if (found) {
                controller.addDiagnostic(orderId, "Battery and its connector are damaged.");

                List<RepairTask> tasks = new ArrayList<>();
                tasks.add(new RepairTask("Replace connector", 200));
                tasks.add(new RepairTask("Replace battery", 1200));
                controller.addTasks(orderId, tasks);
            } else {
                System.out.println("Repair order not found.");
            }

            SummaryDTO summary = controller.getRepairSummary(orderId);
            if (summary != null) {
                showRepairOrderSummary(summary);
            }

            controller.acceptRepair(orderId);

        } catch (CustomerNotFoundException exc) {
            System.out.println("USER MESSAGE: Could not find customer. Check the phone number and try again.");
        } catch (OperationFailedException exc) {
            System.out.println("USER MESSAGE: The customer registry is unavailable. Please try again later.");
            errorLogger.logException(exc);
        }

        testMissingCustomer();
        testDatabaseFailure();
    }

    private void testMissingCustomer() {
        try {
            controller.getCustomer("9999999999");
        } catch (CustomerNotFoundException exc) {
            System.out.println("USER MESSAGE: Could not find customer. Check the phone number and try again.");
        } catch (OperationFailedException exc) {
            System.out.println("USER MESSAGE: The customer registry is unavailable. Please try again later.");
            errorLogger.logException(exc);
        }
    }

    private void testDatabaseFailure() {
        try {
            controller.getCustomer(CustomerRegistry.DATABASE_FAILURE_PHONE_NUMBER);
        } catch (CustomerNotFoundException exc) {
            System.out.println("USER MESSAGE: Could not find customer. Check the phone number and try again.");
        } catch (OperationFailedException exc) {
            System.out.println("USER MESSAGE: The customer registry is unavailable. Please try again later.");
            errorLogger.logException(exc);
        }
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
