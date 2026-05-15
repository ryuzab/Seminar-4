package se.kth.iv1350.repairElectricBike.controller;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.repairElectricBike.dto.CustomerData;
import se.kth.iv1350.repairElectricBike.dto.SummaryDTO;
import se.kth.iv1350.repairElectricBike.integration.CustomerRegistry;
import se.kth.iv1350.repairElectricBike.integration.Date;
import se.kth.iv1350.repairElectricBike.integration.Printer;
import se.kth.iv1350.repairElectricBike.integration.RepairOrderRegistry;
import se.kth.iv1350.repairElectricBike.model.Customer;
import se.kth.iv1350.repairElectricBike.model.RepairOrder;
import se.kth.iv1350.repairElectricBike.model.RepairOrderObserver;
import se.kth.iv1350.repairElectricBike.model.RepairOrderState;
import se.kth.iv1350.repairElectricBike.model.RepairTask;

/**
 * The application's only controller. Handles calls from the view and coordinates model and integration objects.
 */
public class Controller {
    private final RepairOrderRegistry repairOrderRegistry;
    private final CustomerRegistry customerRegistry;
    private final Date date;
    private final Printer printer;

    public Controller(RepairOrderRegistry repairOrderRegistry, CustomerRegistry customerRegistry,
                      Date date, Printer printer) {
        this.repairOrderRegistry = repairOrderRegistry;
        this.customerRegistry = customerRegistry;
        this.date = date;
        this.printer = printer;
    }

    public void addRepairOrderObserver(RepairOrderObserver obs) {
        repairOrderRegistry.addObserver(obs);
    }

    public CustomerData getCustomer(String phoneNumber) throws Exception {
        Customer customer = customerRegistry.findCustomerPhone(phoneNumber);
        if (customer == null) {
            return null;
        }

        return new CustomerData(customer.getName(), customer.getPhoneNumber(), customer.getEmailAddress(),
                customer.getBike().getBrand(), customer.getBike().getModel(), customer.getBike().getSerialNumber());
    }

    public int startRepairOrder(String phoneNumber, String description) throws Exception {
        Customer customer = customerRegistry.findCustomerPhone(phoneNumber);
        if (customer == null) {
            return -1;
        }
        return repairOrderRegistry.createAndAddOrder(customer, customer.getBike(), description);
    }

    public boolean findRepairOrder(int orderId) {
        return repairOrderRegistry.findOrder(orderId) != null;
    }

    public void addDiagnostic(int orderId, String report) {
        RepairOrder order = repairOrderRegistry.findOrder(orderId);
        if (order != null) {
            order.addDiagnostic(report);
            repairOrderRegistry.updateOrder(order);
        }
    }

    public void addTasks(int orderId, String[] taskDescriptions) {
        RepairOrder order = repairOrderRegistry.findOrder(orderId);
        if (order == null) {
            return;
        }
        
        List<RepairTask> tasks = new ArrayList<>();
        float currentTaskCost = RepairTask.BASE_COST; 
        
        for (String description : taskDescriptions) {
            tasks.add(new RepairTask(description, currentTaskCost));
            currentTaskCost += RepairTask.ADDITIONAL_COST; 
        }
        order.addTasks(tasks);
        repairOrderRegistry.updateOrder(order);
    }

    public SummaryDTO getRepairSummary(int orderId) {
        RepairOrder order = repairOrderRegistry.findOrder(orderId);
        if (order == null) {
            return null;
        }
        
        List<String> taskTexts = new ArrayList<>();
        for (RepairTask task : order.getTasks()) {
            taskTexts.add(task.toString());
        }
        return new SummaryDTO(order.getId(), order.getProblemDescription(),
                order.getDiagnosticReport(), taskTexts, order.calculateTotalCost());
    }

    public void acceptRepair(int orderId) {
        RepairOrder order = repairOrderRegistry.findOrder(orderId);
        if (order != null) {
            order.changeState(RepairOrderState.ACCEPTED);
            repairOrderRegistry.updateOrder(order);
            printer.print(order);
        }
    }

    public void rejectRepair(int orderId) {
        RepairOrder order = repairOrderRegistry.findOrder(orderId);
        if (order != null) {
            order.changeState(RepairOrderState.REJECTED);
            repairOrderRegistry.updateOrder(order);
        }
    }

    public String getCurrentDate() {
        return date.getDate();
    }
}