package service;

import dao.OrderDao;
import dao.VehicleDao;
import exception.ExceptionMessages;
import exception.SystemException;
import model.User;
import model.Order;
import model.enums.OrderStatue;
import model.enums.Roles;
import model.enums.VehicleAvailable;
import model.vehicle.VehicleBase;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
    public void createOrder(Order order, User user, long id) throws SystemException {
        if (!user.getRole().equals(Roles.COOPARATE_CUSTOMER) && !user.getRole().equals(Roles.INDIVIDUAL_CUSTOMER)) {
            throw new SystemException(ExceptionMessages.USER_NOT_CUSTOMER);
        }
        boolean isAvailable = orderDao.isVehicleAvailable(id, order.getStartDateTime(), order.getEndDateTime());
        if (!isAvailable) {
            throw new SystemException("Bu araç seçilen tarihlerde zaten rezerve edilmiş.");
        }

        // Connect order and user
        order.setUser(user);
        VehicleDao vd = new VehicleDao();
        VehicleService vehicleService = new VehicleService(vd);

        //AVAILABILITY CHECK
        VehicleBase vehicle = vehicleService.vehicleSearchById(id, user);
        if(vehicle.getAvailable() == VehicleAvailable.AVAILABLE){
            order.setVehicle(vehicle);
            orderDao.save(order);
        } else {
            throw new RuntimeException("Vehicle is not available!");
        }

    }

    public BigDecimal priceCalc(Order order) throws SystemException {
        if (order == null || order.getVehicle() == null) {
            return BigDecimal.ZERO; // TURN 0 IF THERE IS NO VEHICLE
        }

        VehicleBase vehicle = order.getVehicle();
        if (vehicle.getAvailable() == VehicleAvailable.NOTAVAILABLE) {
        return BigDecimal.ZERO;
        }
        BigDecimal price = BigDecimal.ZERO;

        String type = order.getRentalType().toLowerCase();
            switch (type) {

                case "hourly":
                    if(order.getUser().getRole().equals(Roles.COOPARATE_CUSTOMER)){
                        throw new SystemException("Corporate customers must rent at least one month!");
                    }

                    long totalHours = Duration.between(order.getStartDateTime(), order.getEndDateTime()).toHours();
                    totalHours = totalHours > 0 ? totalHours : 1; // minimum 1 HOUR
                    price = BigDecimal.valueOf(vehicle.getHourlyPrice()).multiply(BigDecimal.valueOf(totalHours));
                    break;

                case "daily":
                    int totalDays = calculateTotalDuration(order);
                    if(order.getUser().getRole().equals(Roles.COOPARATE_CUSTOMER)){
                        if(totalDays<30){
                            throw new SystemException("Corporate customers must rent at least one month!");
                        }

                    }
                    price = BigDecimal.valueOf(vehicle.getDailyPrice()).multiply(BigDecimal.valueOf(totalDays));
                    break;

                case "weekly":
                    totalDays = calculateTotalDuration(order);
                    int totalWeeks = (totalDays + 6) / 7;
                    totalWeeks = totalWeeks > 0 ? totalWeeks : 1; // minimum 1 WEEK

                    if(order.getUser().getRole().equals(Roles.COOPARATE_CUSTOMER)){
                        if(totalWeeks<4){
                            throw new SystemException("Corporate customers must rent at least one month!");
                        }
                    }

                    price = BigDecimal.valueOf(vehicle.getWeeklyPrice()).multiply(BigDecimal.valueOf(totalWeeks));
                    break;

                case "monthly":
                    totalDays = calculateTotalDuration(order);
                    int totalMonths = (totalDays + 29) / 30;
                    totalMonths = totalMonths > 0 ? totalMonths : 1; // minimum 1 MONTH
                    price = BigDecimal.valueOf(vehicle.getMonthlyPrice()).multiply(BigDecimal.valueOf(totalMonths));
                    break;
            }

        return price;
    }



    public int calculateTotalDuration(Order order) {
        if (order.getStartDateTime() == null || order.getEndDateTime() == null) return 0;

        String rentalType = order.getRentalType(); // Order sınıfına eklemiş olmalı
        switch (rentalType.toLowerCase()) {
            case "hourly":
                long hours = Duration.between(order.getStartDateTime(), order.getEndDateTime()).toHours();
                return hours > 0 ? (int) hours : 1;
            case "daily":
                int days = (int) Duration.between(order.getStartDateTime(), order.getEndDateTime()).toDays();
                return days > 0 ? days : 1;
            case "weekly":
                int totalDays = (int) Duration.between(order.getStartDateTime(), order.getEndDateTime()).toDays();
                int weeks = (totalDays + 6) / 7;
                return weeks > 0 ? weeks : 1;
            case "monthly":
                totalDays = (int) Duration.between(order.getStartDateTime(), order.getEndDateTime()).toDays();
                int months = (totalDays + 29) / 30;
                return months > 0 ? months : 1;
            default:
                return 0;
        }
    }

    public List<Order> getUserOrders(Long id) {
        List<Order> orders = OrderDao.usersoldOrders(id);
        if (orders == null || orders.isEmpty()) {
            System.out.println("No orders for this user.");
            return new ArrayList<>();
        }

        List<Order> filteredOrders = new ArrayList<>();
        for (Order o : orders) {
            if (o.getCustomerId() == id) {
                filteredOrders.add(o);
                System.out.println(o);
            }
        }

        return filteredOrders;
    }

    public void cancelOrder(Order order) {
        orderDao.cancel(order);
    }


    public static int calculateAge(LocalDate birthDate) {
        if (birthDate == null) {
            return 0; // Doğum tarihi yoksa 0 döndür
        }
        LocalDate today = LocalDate.now();
        return Period.between(birthDate, today).getYears();
    }

}
