package model;

import model.vehicle.VehicleBase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private User customer; //orderin sahibi
    private BigDecimal totalAmount;
    private LocalDateTime orderDate;
    private List<VehicleBase> vehiclerentedList;

    public Order(User customer, BigDecimal totalAmount, LocalDateTime orderDate, List<VehicleBase> vehiclerentedList) {
        this.customer = customer;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.vehiclerentedList = vehiclerentedList;
    }

    public Order() {
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public List<VehicleBase> getVehiclerentedList() {
        return vehiclerentedList;
    }

    public void setVehiclerentedList(List<VehicleBase> vehiclerentedList) {
        this.vehiclerentedList = vehiclerentedList;
    }
}
