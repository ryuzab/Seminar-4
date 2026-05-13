package se.kth.iv1350.repairElectricBike.controller;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.repairElectricBike.dto.CustomerData;
import se.kth.iv1350.repairElectricBike.dto.SummaryDTO;
import se.kth.iv1350.repairElectricBike.integration.CustomerRegistry;
import se.kth.iv1350.repairElectricBike.integration.Date;
import se.kth.iv1350.repairElectricBike.integration.Printer;
import se.kth.iv1350.repairElectricBike.integration.RepairOrderRegistry;
import se.kth.iv1350.repairElectricBike.model.Bike;
import se.kth.iv1350.repairElectricBike.model.Customer;
import se.kth.iv1350.repairElectricBike.model.RepairOrder;
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

    /**
     * Creates a controller.
     *
     * @param repairOrderRegistry Registry for repair orders.
     * @param customerRegistry Registry for customers.
     * @param date Date helper.
     * @param printer Printer integration object.
     */
    public Controller(RepairOrderRegistry repairOrderRegistry, CustomerRegistry customerRegistry,
                      Date date, Printer printer) {
        this.repairOrderRegistry = repairOrderRegistry;
        this.customerRegistry = customerRegistry;
        this.date = date;
        this.printer = printer;
    }

    /**
     * Gets customer data by phone number.
     *
     * @param phoneNumber Customer phone number.
     * @return Customer data, or null if no customer was found.
     */
    public CustomerData getCustomer(String phoneNumber) {
        Customer customer = customerRegistry.findCustomerPhone(phoneNumber);
        if (customer == null) {
            return null;
        }
        Bike bike = customer.getBike();
        return new CustomerData(customer.getName(), customer.getPhoneNumber(), customer.getEmailAddress(),
                bike.getBrand(), bike.getModel(), bike.getSerialNumber());
    }

    /** * Starts a new repair order for a customer.
     *
     * @param phoneNumber Customer phone number.
     * @param description Customer's problem description.
     * @return Created repair order id, or -1 if the customer was not found.
     */
    public int startRepairOrder(String phoneNumber, String description) {
        Customer customer = customerRegistry.findCustomerPhone(phoneNumber);
        if (customer == null) {
            return -1;
        }
        
        // Pass creation responsibility to the registry
        return repairOrderRegistry.createAndAddOrder(customer, customer.getBike(), description);
    }

    /**
     * Checks if a repair order exists in the registry.
     *
     * @param orderId Repair order id.
     * @return true if the repair order was found, otherwise false.
     */
    public boolean findRepairOrder(int orderId) {
        return repairOrderRegistry.findOrder(orderId) != null;
    }

    /**
     * Adds a technician diagnostic report to the specified repair order.
     *
     * @param orderId Repair order id.
     * @param report Diagnostic report.
     */
    public void addDiagnostic(int orderId, String report) {
        RepairOrder order = repairOrderRegistry.findOrder(orderId);
        if (order != null) {
            order.addDiagnostic(report);
            repairOrderRegistry.updateOrder(order);
        }
    }

    /**
     * Adds repair tasks to the specified repair order and stores the updated order in the registry.
     *
     * @param orderId Repair order id.
     * @param taskDescriptions Task descriptions.
     */
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

    /**
     * Gets a summary for the specified repair order.
     *
     * @param orderId Repair order id.
     * @return Repair summary, or null if there is no matching repair order.
     */
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

    /**
     * Accepts the specified repair order and prints it.
     * * @param orderId Repair order id.
     */
    public void acceptRepair(int orderId) {
        RepairOrder order = repairOrderRegistry.findOrder(orderId);
        if (order != null) {
            order.changeState(RepairOrderState.ACCEPTED);
            repairOrderRegistry.updateOrder(order);
            printer.print(order);
        }
    }

    /**
     * Rejects the specified repair order.
     * * @param orderId Repair order id.
     */
    public void rejectRepair(int orderId) {
        RepairOrder order = repairOrderRegistry.findOrder(orderId);
        if (order != null) {
            order.changeState(RepairOrderState.REJECTED);
            repairOrderRegistry.updateOrder(order);
        }
    }

    /**
     * Gets the date used by this controller.
     *
     * @return Today's date.
     */
    public String getCurrentDate() {
        return date.getDate();
    }
}