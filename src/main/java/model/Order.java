package model;

import model.enums.OrderStatue;
import model.vehicle.VehicleBase;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {
    private long id;
    private long customerId;       // eklendi
    private long vehicleId;        // eklendi
    private User user;
    private VehicleBase vehicle;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private BigDecimal totalAmount;
    private LocalDateTime orderDate;
    private String rentalType;
    private OrderStatue statue;

    public OrderStatue getStatue() {
        return statue;
    }

    public void setStatue(OrderStatue statue) {
        this.statue = statue;
    }

    public Order() {
        this.orderDate = LocalDateTime.now();
    }

    public Order(User user, VehicleBase vehicle, LocalDateTime startDateTime, LocalDateTime endDateTime, BigDecimal totalAmount, String rentalType) {
        this.user = user;
        this.vehicle = vehicle;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.totalAmount = totalAmount;
        this.rentalType = rentalType;
        this.orderDate = LocalDateTime.now();
        this.statue = OrderStatue.ACTIVE;
    }

    // GETTER / SETTER
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getCustomerId() { return customerId; }
    public void setCustomerId(long customerId) { this.customerId = customerId;  }

    public long getVehicleId() { return vehicleId; }
    public void setVehicleId(long vehicleId) { this.vehicleId = vehicleId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public VehicleBase getVehicle() { return vehicle; }
    public void setVehicle(VehicleBase vehicle) { this.vehicle = vehicle; }

    public LocalDateTime getStartDateTime() { return startDateTime; }
    public void setStartDateTime(LocalDateTime startDateTime) { this.startDateTime = startDateTime; }

    public LocalDateTime getEndDateTime() { return endDateTime; }
    public void setEndDateTime(LocalDateTime endDateTime) { this.endDateTime = endDateTime; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public String getRentalType() { return rentalType; }
    public void setRentalType(String rentalType) { this.rentalType = rentalType; }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", vehicleId=" + vehicleId +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", totalAmount=" + totalAmount +
                ", orderDate=" + orderDate +
                ", statue=" + (statue != null ? statue : OrderStatue.ACTIVE)  +
                '}';
    }
}
