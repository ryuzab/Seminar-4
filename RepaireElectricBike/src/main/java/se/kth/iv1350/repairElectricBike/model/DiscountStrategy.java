package se.kth.iv1350.repairElectricBike.model;

/** Calculates a discount for a repair order. */
public interface DiscountStrategy {
    /**
     * Calculates the discount for the specified repair order.
     *
     * @param repairOrder The repair order to discount.
     * @return The discount amount in SEK.
     */
    float calculateDiscount(RepairOrder repairOrder);
}
