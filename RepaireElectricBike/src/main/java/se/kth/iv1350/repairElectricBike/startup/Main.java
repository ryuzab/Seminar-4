package se.kth.iv1350.repairElectricBike.startup;

import se.kth.iv1350.repairElectricBike.controller.Controller;
import se.kth.iv1350.repairElectricBike.integration.CustomerRegistry;
import se.kth.iv1350.repairElectricBike.integration.Date;
import se.kth.iv1350.repairElectricBike.integration.Printer;
import se.kth.iv1350.repairElectricBike.integration.RepairOrderRegistry;
import se.kth.iv1350.repairElectricBike.view.View;

public class Main {
    public static void main(String[] args) {
        // USE getInstance() INSTEAD OF new
        RepairOrderRegistry repairOrderRegistry = RepairOrderRegistry.getInstance();
        CustomerRegistry customerRegistry = CustomerRegistry.getInstance();
        
        Date date = new Date();
        Printer printer = new Printer();
        Controller controller = new Controller(repairOrderRegistry, customerRegistry, date, printer);
        View view = new View(controller);
        view.runBasicFlow();
    }
}