package se.kth.iv1350.repairElectricBike.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.repairElectricBike.model.Bike;
import se.kth.iv1350.repairElectricBike.model.Customer;
import se.kth.iv1350.repairElectricBike.model.RepairOrder;

/**
 * Stores and finds repair orders. Replaces a database in this seminar solution.
 */
public class RepairOrderRegistry {
    private final List<RepairOrder> repairOrders = new ArrayList<>();
    private int nextOrderId = 1;

    /**
     * Creates an empty repair order registry.
     */
    public RepairOrderRegistry() {
    }

    /**
     * Generates a new OrderId incrementing from the value of nextOrderId.
     * * @return The newly generated OrderId.
     */
    private int generateNextOrderId() {
        return nextOrderId++;
    }

    /**
     * Creates a new order, assigns it an ID, adds it to the registry, and returns the ID.
     */
    public int createAndAddOrder(Customer customer, Bike bike, String description) {
        int newId = generateNextOrderId();
        RepairOrder newOrder = new RepairOrder(newId, customer, bike, description);
        addOrder(newOrder);
        return newId;
    }

    /**
     * Adds a repair order to the registry if it is not already stored.
     *
     * @param repairOrder Repair order to add.
     */
    public void addOrder(RepairOrder repairOrder) {
        if (findOrder(repairOrder.getId()) == null) {
            // Store a defensive copy
            repairOrders.add(new RepairOrder(repairOrder));
        }
    }

    /**
     * Finds one specific repair order by id.
     *
     * @param orderId Repair order id.
     * @return A copy of the matching repair order, or null if no order was found.
     */
    public RepairOrder findOrder(int orderId) {
        for (RepairOrder repairOrder : repairOrders) {
            if (repairOrder.getId() == orderId) {
                // Return a defensive copy
                return new RepairOrder(repairOrder);
            }
        }
        return null;
    }

    /**
     * Updates a stored repair order by replacing the order with the same id.
     *
     * @param repairOrder Updated repair order.
     */
    public void updateOrder(RepairOrder repairOrder) {
        for (int i = 0; i < repairOrders.size(); i++) {
            if (repairOrders.get(i).getId() == repairOrder.getId()) {
                // Replace with a fresh copy
                repairOrders.set(i, new RepairOrder(repairOrder));
                return;
            }
        }
        addOrder(repairOrder);
    }

    /**
     * Finds all repair orders connected to a phone number.
     *
     * @param phoneNumber Customer phone number.
     * @return Repair orders for the customer.
     */
    public List<RepairOrder> findOrdersByPhoneNumber(String phoneNumber) {
        List<RepairOrder> matches = new ArrayList<>();
        for (RepairOrder repairOrder : repairOrders) {
            if (repairOrder.getCustomer().getPhoneNumber().equals(phoneNumber)) {
                // Add defensive copies to the returned list
                matches.add(new RepairOrder(repairOrder));
            }
        }
        return matches;
    }
}