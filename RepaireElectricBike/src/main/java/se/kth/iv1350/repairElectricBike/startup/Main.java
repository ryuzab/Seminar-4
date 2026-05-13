package se.kth.iv1350.repairElectricBike.startup;

import se.kth.iv1350.repairElectricBike.controller.Controller;
import se.kth.iv1350.repairElectricBike.integration.CustomerRegistry;
import se.kth.iv1350.repairElectricBike.integration.Date;
import se.kth.iv1350.repairElectricBike.integration.Printer;
import se.kth.iv1350.repairElectricBike.integration.RepairOrderRegistry;
import se.kth.iv1350.repairElectricBike.view.View;

/*
 * Starts the Repair Electric Bike application.
 */
public class Main {
    /**
     * Starts the application.
     *
     * @param args Command line arguments, not used.
     */
    public static void main(String[] args) {
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();
        CustomerRegistry customerRegistry = new CustomerRegistry();
        Date date = new Date();
        Printer printer = new Printer();
        Controller controller = new Controller(repairOrderRegistry, customerRegistry, date, printer);
        View view = new View(controller);
        view.runBasicFlow();
    }

}
