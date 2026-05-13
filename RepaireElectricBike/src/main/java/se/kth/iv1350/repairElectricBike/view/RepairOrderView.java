package se.kth.iv1350.repairElectricBike.view;

import se.kth.iv1350.repairElectricBike.model.DiscountStrategy;
import se.kth.iv1350.repairElectricBike.model.RepairOrder;
import se.kth.iv1350.repairElectricBike.model.RepairOrderObserver;

public class RepairOrderView implements RepairOrderObserver {
    @Override
    public void repairOrderUpdated(RepairOrder repairOrder) {
        System.out.println("\n--- REPAIR ORDER UPDATED ---");
        System.out.println(repairOrder);
        System.out.println("----------------------------");
    }

    private DiscountStrategy discountStrategy;

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
    this.discountStrategy = discountStrategy;
}
}