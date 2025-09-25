package service;

import dao.OrderDao;
import model.User;
import model.Order;
import model.vehicle.VehicleBase;

import java.math.BigDecimal;
import java.util.List;

public class OrderService {
    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
    private void choisePriceOption(VehicleBase vehicle){
        if(vehicle.getHourlyPrice() == 0 &&
                vehicle.getDailyPrice() == 0 && vehicle.getMonthlyPrice() == 0 && vehicle.getWeeklyPrice()==0){

            try {
                throw new IllegalAccessException("Have to Choose at least one price type!");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    UserService customerService = new UserService();
    public Order save(User customer, List<VehicleBase> productList){

        Order order = new Order();
        BigDecimal totalAmount = productList.stream()
                .map(v-> BigDecimal.valueOf(v.getDailyPrice()))
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        return order;
    }
}
