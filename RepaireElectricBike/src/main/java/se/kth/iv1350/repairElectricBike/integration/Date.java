package se.kth.iv1350.repairElectricBike.integration;

import java.time.LocalDate;

/**
 * Responsible for getting the current date.
 */
public class Date {
    /**
     * Creates a date helper.
     */
    public Date() {
    }

    /**
     * Gets today's date.
     *
     * @return Today's date as text.
     */
    public String getDate() {
        return LocalDate.now().toString();
    }
}
