package se.kth.iv1350.repairElectricBike.view;

import se.kth.iv1350.repairElectricBike.integration.FileLogger;
import se.kth.iv1350.repairElectricBike.model.RepairOrder;
import se.kth.iv1350.repairElectricBike.model.RepairOrderObserver;

/** Observer that logs updated repair orders to a file. */
public class RepairOrderLogger implements RepairOrderObserver {
    private final FileLogger logger;

    /**
     * Creates a logger observer.
     *
     * @param fileName The file to log repair orders to.
     */
    public RepairOrderLogger(String fileName) {
        this.logger = new FileLogger(fileName);
    }

    @Override
    public void repairOrderUpdated(RepairOrder repairOrder) {
        logger.logText("UPDATED REPAIR ORDER: " + repairOrder);
    }
}
