package se.kth.iv1350.repairElectricBike.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a repair order for one customer's bike.
 */
public class RepairOrder {
    private final int id;
    private final Customer customer;
    private final Bike bike;
    private final String problemDescription;
    private String diagnosticReport;
    private final List<RepairTask> tasks = new ArrayList<>();
    private RepairOrderState state;

    /**
     * Creates a repair order.
     *
     * @param id Repair order id.
     * @param customer Customer owning the bike.
     * @param bike Bike to repair.
     * @param problemDescription Customer's problem description.
     */
    public RepairOrder(int id, Customer customer, Bike bike, String problemDescription) {
        this.id = id;
        this.customer = customer;
        this.bike = bike;
        this.problemDescription = problemDescription;
        this.state = RepairOrderState.NEWLY_CREATED;
        this.diagnosticReport = "";
    }

    /**
     * Copy constructor for creating defensive copies.
     * * @param other The RepairOrder to copy.
     */
    public RepairOrder(RepairOrder other) {
        this.id = other.id;
        this.customer = other.customer;
        this.bike = other.bike;
        this.problemDescription = other.problemDescription;
        this.diagnosticReport = other.diagnosticReport;
        this.state = other.state;
        this.tasks.addAll(other.tasks);
    }

    /** @return Repair order id. */
    public int getId() { return id; }

    /** @return Customer owning the repair order. */
    public Customer getCustomer() { return customer; }

    /** @return Bike to repair. */
    public Bike getBike() { return bike; }

    /** @return Customer's problem description. */
    public String getProblemDescription() { return problemDescription; }

    /** @return Diagnostic report. */
    public String getDiagnosticReport() { return diagnosticReport; }

    /** @return Repair tasks. */
    public List<RepairTask> getTasks() { return Collections.unmodifiableList(tasks); }

    /** @return Current repair order state. */
    public RepairOrderState getState() { return state; }

    /**
     * Adds a technician diagnostic report.
     *
     * @param report Diagnostic report.
     */
    public void addDiagnostic(String report) {
        this.diagnosticReport = report;
    }

    /**
     * Adds repair tasks and marks the order ready for approval.
     *
     * @param newTasks Tasks needed for the repair.
     */
    public void addTasks(List<RepairTask> newTasks) {
        this.tasks.clear();
        this.tasks.addAll(newTasks);
        this.state = RepairOrderState.READY_FOR_APPROVAL;
    }

    /**
     * Calculates total cost for all repair tasks.
     *
     * @return Total repair cost.
     */
    public float calculateTotalCost() {
        float total = 0;
        for (RepairTask task : tasks) {
            total += task.getCost();
        }
        return total;
    }

    /**
     * Changes repair order state.
     *
     * @param state New state.
     */
    public void changeState(RepairOrderState state) {
        this.state = state;
    }
}