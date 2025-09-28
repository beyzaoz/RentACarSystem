package service;

import dao.PaymentDao;
import model.Order;
import model.Payment;
import model.enums.Roles;
import model.vehicle.VehicleBase;

import java.math.BigDecimal;

public class PaymentService {
    public final PaymentDao paymentDao;

    public PaymentService() {
        this.paymentDao = new PaymentDao();
    }

    public static BigDecimal depositCalculation(Payment payment, Order order, VehicleBase vb) {
        BigDecimal baseDepositPercentage = BigDecimal.valueOf(5); // %5 default
        BigDecimal extraDepositPercentage = BigDecimal.valueOf(10); // %10

        int age = OrderService.calculateAge(order.getUser().getBirth_date());
        BigDecimal vehiclePrice = BigDecimal.valueOf(vb.getVehiclePrice());


        if (vehiclePrice.compareTo(BigDecimal.valueOf(2000000)) > 0) {
            if (age >= 30) {
                baseDepositPercentage = extraDepositPercentage;
                System.out.println("Vehicle price > 2.000.000 and renter >=30 age need to pay EXTRA 10% deposit");
            } else {
                System.out.println("Vehicle price > 2.000.000 but renter under 30, deposit is 5%");
            }
        } else {
            System.out.println("Vehicle price <= 2.000.000, deposit is 5%");
        }

        BigDecimal deposit = payment.getTotalAmount()
                .multiply(baseDepositPercentage)
                .divide(BigDecimal.valueOf(100));


        return deposit;
    }


}
