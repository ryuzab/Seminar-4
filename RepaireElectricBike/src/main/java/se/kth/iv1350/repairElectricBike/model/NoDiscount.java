package se.kth.iv1350.repairElectricBike.model;

/** Discount strategy that gives no discount. */
public class NoDiscount implements DiscountStrategy {
    @Override
    public float calculateDiscount(RepairOrder repairOrder) {
        return 0;
    }
}
