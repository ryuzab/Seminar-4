package se.kth.iv1350.repairElectricBike.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.repairElectricBike.model.Bike;
import se.kth.iv1350.repairElectricBike.model.Customer;
import se.kth.iv1350.repairElectricBike.model.RepairOrder;
import se.kth.iv1350.repairElectricBike.model.RepairOrderObserver;

public class RepairOrderRegistry {
    private final List<RepairOrder> repairOrders = new ArrayList<>();
    private final List<RepairOrderObserver> observers = new ArrayList<>();
    private int nextOrderId = 1;

    // 1. Static instance
    private static final RepairOrderRegistry INSTANCE = new RepairOrderRegistry();

    // 2. PRIVATE constructor
    private RepairOrderRegistry() {
    }

    // 3. Global access point
    public static RepairOrderRegistry getInstance() {
        return INSTANCE;
    }

    public void addObserver(RepairOrderObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(RepairOrder repairOrder) {
        RepairOrder copy = new RepairOrder(repairOrder);
        for (RepairOrderObserver observer : observers) {
            observer.repairOrderUpdated(copy);
        }
    }

    private int generateNextOrderId() {
        return nextOrderId++;
    }

    public int createAndAddOrder(Customer customer, Bike bike, String description) {
        int newId = generateNextOrderId();
        RepairOrder newOrder = new RepairOrder(newId, customer, bike, description);
        addOrder(newOrder);
        notifyObservers(newOrder);
        return newId;
    }

    public void addOrder(RepairOrder repairOrder) {
        if (findOrder(repairOrder.getId()) == null) {
            repairOrders.add(new RepairOrder(repairOrder));
        }
    }

    public RepairOrder findOrder(int orderId) {
        for (RepairOrder repairOrder : repairOrders) {
            if (repairOrder.getId() == orderId) {
                return new RepairOrder(repairOrder);
            }
        }
        return null;
    }

    public void updateOrder(RepairOrder repairOrder) {
        for (int i = 0; i < repairOrders.size(); i++) {
            if (repairOrders.get(i).getId() == repairOrder.getId()) {
                repairOrders.set(i, new RepairOrder(repairOrder));
                notifyObservers(repairOrder);
                return;
            }
        }
        addOrder(repairOrder);
        notifyObservers(repairOrder);
    }

    public List<RepairOrder> findOrdersByPhoneNumber(String phoneNumber) {
        List<RepairOrder> matches = new ArrayList<>();
        for (RepairOrder repairOrder : repairOrders) {
            if (repairOrder.getCustomer().getPhoneNumber().equals(phoneNumber)) {
                matches.add(new RepairOrder(repairOrder));
            }
        }
        return matches;
    }
}