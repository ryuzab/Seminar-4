package se.kth.iv1350.repairElectricBike.dto;

import java.util.List;

/**
 * Data transfer object with the information needed to show a repair summary.
 */
public class SummaryDTO {
    private final int orderId;
    private final String problemDescription;
    private final String diagnosticReport;
    private final List<String> tasks;
    private final float totalCost;

    /**
     * Creates a repair summary.
     *
     * @param orderId Repair order id.
     * @param problemDescription Customer's problem description.
     * @param diagnosticReport Technician's diagnostic report.
     * @param tasks Tasks needed to repair the bike.
     * @param totalCost Total estimated cost.
     */
    public SummaryDTO(int orderId, String problemDescription, String diagnosticReport,
                      List<String> tasks, float totalCost) {
        this.orderId = orderId;
        this.problemDescription = problemDescription;
        this.diagnosticReport = diagnosticReport;
        this.tasks = List.copyOf(tasks);
        this.totalCost = totalCost;
    }

    /** @return Repair order id. */
    public int getOrderId() { return orderId; }

    /** @return Customer's problem description. */
    public String getProblemDescription() { return problemDescription; }

    /** @return Technician's diagnostic report. */
    public String getDiagnosticReport() { return diagnosticReport; }

    /** @return Tasks needed to repair the bike. */
    public List<String> getTasks() { return tasks; }

    /** @return Total estimated cost. */
    public float getTotalCost() { return totalCost; }

    /** @return Printable summary. */
    @Override
    public String toString() {
        return "Repair order " + orderId + "\nProblem: " + problemDescription
                + "\nDiagnostic: " + diagnosticReport + "\nTasks: " + tasks
                + "\nTotal cost: " + totalCost + " SEK";
    }
}
