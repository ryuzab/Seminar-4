package se.kth.iv1350.repairElectricBike.view;

import se.kth.iv1350.repairElectricBike.model.RepairOrder;
import se.kth.iv1350.repairElectricBike.model.RepairOrderObserver;

/** Observer that prints updated repair orders to System.out. */
public class RepairOrderView implements RepairOrderObserver {
    @Override
    public void repairOrderUpdated(RepairOrder repairOrder) {
        System.out.println("\n--- REPAIR ORDER UPDATED ---");
        System.out.println(repairOrder);
        System.out.println("----------------------------");
    }
}
