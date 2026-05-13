package se.kth.iv1350.repairElectricBike.model;

/**
 * Represents one task needed to repair a bike.
 */
public class RepairTask {
    private final String description;
    private final float cost;
    public static final float BASE_COST = 500.0f;
    public static final float ADDITIONAL_COST = 250.0f;

    /**
     * Creates a repair task.
     *
     * @param description Task description.
     * @param cost Task cost.
     */
    public RepairTask(String description, float cost) {
        this.description = description;
        this.cost = cost;
    }

    /** @return Task description. */
    public String getDescription() { return description; }

    /** @return Task cost. */
    public float getCost() { return cost; }

    /** @return Printable task. */
    @Override
    public String toString() {
        return description + " (" + cost + " SEK)";
    }
}
