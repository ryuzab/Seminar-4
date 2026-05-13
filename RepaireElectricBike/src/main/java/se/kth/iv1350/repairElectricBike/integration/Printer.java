package se.kth.iv1350.repairElectricBike.integration;

import se.kth.iv1350.repairElectricBike.model.RepairOrder;

/**
 * Responsible for sending repair order printouts to an external printer.
 */
public class Printer {
    /**
     * Creates a printer.
     */
    public Printer() {
    }

    /**
     * Prints a repair order to System.out in this seminar solution.
     *
     * @param repairOrder Repair order to print.
     */
    public void print(RepairOrder repairOrder) {
        System.out.println("\n--- REPAIR ORDER PRINTOUT ---");
        System.out.println("Order id: " + repairOrder.getId());
        System.out.println("Customer: " + repairOrder.getCustomer().getName());
        System.out.println("Bike: " + repairOrder.getBike().getBrand() + " " + repairOrder.getBike().getModel());
        System.out.println("State: " + repairOrder.getState());
        System.out.println("Total cost: " + repairOrder.calculateTotalCost() + " SEK");
        System.out.println("-----------------------------\n");
    }
}
