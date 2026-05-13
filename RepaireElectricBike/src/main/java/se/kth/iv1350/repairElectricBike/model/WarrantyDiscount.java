package se.kth.iv1350.repairElectricBike.model;

public class WarrantyDiscount implements DiscountStrategy {
    private static final float DISCOUNT_RATE = 0.10f;

    @Override
    public float calculateDiscount(RepairOrder repairOrder) {
        return repairOrder.calculateTotalCostBeforeDiscount() * DISCOUNT_RATE;
    }
}