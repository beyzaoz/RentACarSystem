package UI;

import dao.OrderDao;
import dao.VehicleDao;
import exception.ExceptionMessages;
import exception.SystemException;
import model.Order;
import model.Payment;
import model.User;
import model.enums.OrderStatue;
import model.enums.PaymentMethod;
import model.vehicle.Car;
import model.vehicle.Helicopter;
import model.vehicle.Motorcycle;
import model.vehicle.VehicleBase;
import service.OrderService;
import service.PaymentService;
import service.VehicleService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CustomerLogin {

    public static void customerLoginPage(Scanner s, User user) throws SystemException {

       while(true) {
           System.out.println("WELCOME " + user.getFirst_name().toUpperCase());
           System.out.println("1-Search Vehicle");
           System.out.println("2-Rent A Vehicle");
           System.out.println("3-Old Orders");
           System.out.println("4-Order Cancelations");
           System.out.println("5-Filter");
           System.out.println("6-Exit");
           System.out.println("Please select one option: ");
           String option = s.nextLine();

           switch (option) {
               case "1":
                   searchVehicle(s, user);
                   break;
               case "2":
                   rentVehicle(user); //todo
                   break;
               case "3":
                   oldOrders(user); //todo
                   break;
               case "4":
                   VehicleBase vehicleBase = new VehicleBase();
                   orderCancelation(user);
                   break;
               case "5":
                   vehicleFiltering(s, user);
                   break;
               case "6":
                   return;
           }
       }

    }

    private static void vehicleFiltering(Scanner s, User user) throws SystemException {
        VehicleDao vehicleDao = new VehicleDao();
        VehicleService v = new VehicleService(vehicleDao);
        while (true) { // Döngü başlatıyoruz
            System.out.println("Filter BY: ");
            System.out.println("1-Type");
            System.out.println("2-Brand");
            System.out.println("3-Price");
            System.out.println("4-Exit");
            System.out.print("Your Choise: ");
            String choise = s.nextLine();

            switch (choise) {
                case "1":
                    searchedWithType(s, user, v);
                    break;
                case "2":
                    searchedWithBrand(s, user, v);
                    break;
                case "3":
                    searchedWithPrice(s, user, v);
                    break;
                case "4":
                    return; // Exit seçilirse metottan çık
                default:
                    System.out.println("Invalid choice! Try again.");
            }
            System.out.println(); // Görsellik için boş satır
        }

    }

    private static void oldOrders(User user) {
        OrderDao orderDao = new OrderDao();
        List<Order> orders = orderDao.usersoldOrders(user.getId());

        if (orders == null || orders.isEmpty()) {
            System.out.println("No orders for this user.");
            return;
        }

        for (Order o : orders) {
            if (o.getCustomerId() == user.getId()) {
                System.out.println(o);
            }
        }
    }

    private static void orderCancelation(User user) {
        OrderDao orderDao = new OrderDao();
        OrderService orderService = new OrderService(orderDao);
        List<Order> orders = orderService.getUserOrders(user.getId());

        List<Order> activeOrders = new ArrayList<>();
        for (Order order : orders){
            if (order.getStatue().equals(OrderStatue.ACTIVE))
                activeOrders.add(order);
        }
//                orders.stream()
//                .filter(order -> order.getStatue().equals(OrderStatue.ACTIVE)).toList();

        if (!activeOrders.isEmpty()) {
            System.out.println("Your Active Orders:");
            for (Order o : activeOrders) {
                if (o.getVehicle() != null) {
                    System.out.println(o.getId() + " | " + o.getVehicle().getVehicleType() + " | "
                            + o.getVehicle().getVehiclebrand() + " | Total: " + o.getTotalAmount());
                } else {
                    System.out.println(o.getId() + " | Vehicle info not available | Total: " + o.getTotalAmount());
                }
            }

            Scanner s = new Scanner(System.in);
            System.out.println("Enter Order ID to cancel: ");
            long orderId = s.nextLong();
            s.nextLine();

            Order orderToCancel = activeOrders.stream()
                    .filter(o -> o.getId() == orderId)
                    .findFirst()
                    .orElse(null);

            if (orderToCancel == null) {
                System.out.println("Order not found or already canceled!");
                return;
            }

            orderService.cancelOrder(orderToCancel); // DB'de status CANCELED olacak
            System.out.println("Order cancelled successfully!");
        }

    }

    private static void rentVehicle(User user) throws SystemException {

        Scanner s = new Scanner(System.in);
        searchVehicle(s, user);

        try {
            System.out.println("Please Choose a vehicle and type the vehicle ID: ");
            long id = s.nextLong();
            s.nextLine();

            Order order = new Order();
            order.setUser(user);

            VehicleDao vd = new VehicleDao();
            VehicleService vs = new VehicleService(vd);
            VehicleBase v = vs.vehicleSearchById(id, user);


            if (v == null) {
                System.out.println("Vehicle not Found!");
                return;
            }


            double vehiclePrice = v.getVehiclePrice();

            String rentalType;
            LocalDateTime pickupDate;
            LocalDateTime dropOffDate;




            while (true) {
                System.out.println("Rent Hourly/Daily/Weekly/Monthly:  ");
                rentalType = s.nextLine().trim().toLowerCase();

                if (rentalType.equals("hourly") || rentalType.equals("daily") || rentalType.equals("weekly") || rentalType.equals("monthly")) {

                    order.setRentalType(rentalType);
                    break; // geçerli, döngüden çık
                } else {
                    System.out.println("Have to Choose at least one price type!");
                }

            }


            while (true) {
                System.out.println("Please Choose a pickup date and time (yyyy-MM-dd HH:mm): ");
                String pickupInput = s.nextLine();
                if (pickupInput.isEmpty()) {
                    System.out.println("Pickup Date cannot be EMPTY!");
                    continue;
                }
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    pickupDate = LocalDateTime.parse(pickupInput, formatter);
                    order.setStartDateTime(pickupDate);
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format! Please use yyyy-MM-dd HH:mm");
                }
            }

            while (true) {
                System.out.println("Please Choose a drop-off date and time (yyyy-MM-dd HH:mm): ");
                String dropOffInput = s.nextLine();
                if (dropOffInput.isEmpty()) {
                    System.out.println("Drop-off Date cannot be EMPTY!");
                    continue;
                }
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    dropOffDate = LocalDateTime.parse(dropOffInput, formatter);
                    order.setEndDateTime(dropOffDate);
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format! Please use yyyy-MM-dd HH:mm");
                }
            }

            if(dropOffDate.isBefore(pickupDate)) { //Drop-off date cannot be earlier than pickup date
                System.out.println("Drop-off date cannot be before pickup date!");
                return;
            }


            OrderDao od = new OrderDao();
            OrderService orderService = new OrderService(od);
            boolean available = od.isVehicleAvailable(id, pickupDate, dropOffDate);
            if(!available){
                System.out.println("This vehicle is already reserved for the selected dates!");
                return;
            }

            PaymentService ps = new PaymentService();
            Payment payment = new Payment();

            order.setVehicle(v);
            BigDecimal totalprice = orderService.priceCalc(order);
            payment.setTotalAmount(totalprice);
            order.setTotalAmount(totalprice);
            BigDecimal deposite = PaymentService.depositCalculation(payment, order, v);
            payment.setDeposite(deposite);

            System.out.println("Your Total price is : " + totalprice);
            System.out.println("Your Deposite : " + deposite);
            paymentPage(order, payment, v);
            orderService.createOrder(order, user, id);
        } catch (SystemException e) {
            System.out.println(e.getMessage());
            rentVehicle(user);
        }

    }

    private static boolean paymentPage(Order order, Payment payment, VehicleBase vb) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please choose your Payment Method: ");
        System.out.println("1-Credit card ");
        System.out.println("2-Debit card ");
        System.out.println("3-CASHAPP ");
        System.out.println("4-PAYPALL ");
        System.out.println("5-ZELLE ");
        String choice = s.nextLine().trim();
        String message = "You can Pay in store!";

        boolean paymentSuccess = false;

        switch (choice) {
            case "1" -> {
                payment.setPaymentMethod(PaymentMethod.CREDIT_CARD);
                paymentSuccess = true;
            }
            case "2" -> {
                payment.setPaymentMethod(PaymentMethod.DEBIT_CARD);
                paymentSuccess = true;
            }
            case "3" -> {
                payment.setPaymentMethod(PaymentMethod.CASHAPP);
                paymentSuccess = true;
            }
            case "4" -> {
                payment.setPaymentMethod(PaymentMethod.PAYPALL);
                paymentSuccess = true;
            }
            case "5" -> {
                payment.setPaymentMethod(PaymentMethod.ZELLE);
                paymentSuccess = true;
            }
            default -> {
                System.out.println("Invalid choice!");
                return false;
            }
        }

        if(paymentSuccess) {
            System.out.println(message);
            System.out.println("--Payment Completed--");
        }

        return paymentSuccess; // true ise ödeme başarılı
    }

    private static void searchedWithPrice(Scanner s, User user, VehicleService v) throws SystemException {
        String rentalType = "";
        while (true) {
            System.out.println("Rent Hourly/Daily/Weekly/Monthly: ");
            rentalType = s.nextLine().trim().toLowerCase();

            if (rentalType.equals("hourly") || rentalType.equals("daily") || rentalType.equals("weekly") || rentalType.equals("monthly")) {
                break; // geçerli, döngüden çık
            } else {
                System.out.println("Have to Choose at least one price type!");
            }}

            System.out.println("Minimum Price: ");
            BigDecimal minPrice = s.nextBigDecimal();

            System.out.println("Maximum Price: ");
            BigDecimal maxPrice = s.nextBigDecimal();
            List<VehicleBase> vehicles = v.vehicleSearchByPrice(rentalType, minPrice, maxPrice, user);

            if (vehicles.isEmpty()) {
                System.out.println("No vehicles found in this price range.");
            } else {
                for (VehicleBase vb : vehicles) {
                    printVehicleInfoCustomer(vb);
                }
            }

    }

    private static void searchVehicle(Scanner s, User user) throws SystemException {

        while (true) {
            System.out.println("Search By: ");
            System.out.println("1-ID ");
            System.out.println("2-Vehicle Type ");
            System.out.println("3-Exit ");
            System.out.println("Your choise: ");
            String choise = s.nextLine();


            VehicleDao vehicleDao = new VehicleDao();
            VehicleService vehicleService = new VehicleService(vehicleDao);

            switch (choise) {
                case "1":
                    searchedWithID(s, user, vehicleService);
                    break;
                case "2":
                    searchedWithType(s, user, vehicleService);
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid Option. Please try again!");

            }
            return;

        }
    }

    private static void searchedWithType(Scanner s, User user, VehicleService vehicleService) throws SystemException {
        try {
            String vehicletype = "";
            //Type check
            while (true) {
                System.out.println("Please enter Vehicle type(Car/Helicopter/Motorcycle)");
                vehicletype = s.nextLine().toUpperCase();
                List<VehicleBase> vb = vehicleService.vehicleSearchByType(vehicletype, user);

                if (vb != null && !vb.isEmpty()) {
                    System.out.println("Searching...");
                    Thread.sleep(3000); //3 second
                    for (VehicleBase t : vb) {
                        printVehicleInfoCustomer(t);
                    }
                } else {
                    System.out.println("Invalid Choise! Please try again.");
                    continue; //if it is wrong ask the same question again!
                }
                break;

            }
        } catch (SystemException | InterruptedException e) {
            throw new SystemException(ExceptionMessages.SEARCHED_WITH_TYPE_ERROR);
        }
    }

    private static void searchedWithID(Scanner s, User user, VehicleService vehicleService) {
        try {
            long vehicleid;

            //ID CHECK-----
            while (true) {
                System.out.println("Please enter vehicle ID: ");
                try {
                    vehicleid = s.nextLong();
                    s.nextLine();
                    VehicleBase vb = vehicleService.vehicleSearchById(vehicleid, user);
                    if (vb != null && vb.getId() != 0) {
                        System.out.println("Searching...");
                        Thread.sleep(3000); //3 second
                        printVehicleInfoCustomer(vb);

                    } else {
                        System.out.println("Invalid Id! Please Try Again");
                        continue;
                    }
                } catch (SystemException e) {
                    throw new SystemException(ExceptionMessages.INPUT_MISMATCH_ERROR);

                } catch (InterruptedException e) {
                    throw new SystemException(ExceptionMessages.SEARCHED_WITH_BY_ID_ERROR);
                }
                break;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void searchedWithBrand(Scanner s, User user, VehicleService vehicleService) {
        try {
            System.out.println("Please enter vehicle brand: ");
            String vehicleBrand = s.nextLine();

            List<VehicleBase> vbList = vehicleService.vehicleSearchByBrand(vehicleBrand, user);
            if (!vbList.isEmpty()) {
                System.out.println("Searching...");
                Thread.sleep(3000);
                for (VehicleBase vb : vbList) {
                    printVehicleInfoCustomer(vb);
                }
            } else {
                System.out.println("No vehicles found with this brand.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printVehicleInfoCustomer(VehicleBase vb) {
        System.out.print(vb.getId() + " | " + vb.getVehicleType() + " | " + vb.getVehiclemodel() + " | Hourly: " + vb.getHourlyPrice() + " | Daily: " + vb.getDailyPrice() + " | Weekly: " + vb.getWeeklyPrice() + " | Monthly: " + vb.getMonthlyPrice() + " | Availability: " + vb.getAvailable());

        if (vb instanceof Car car) {
            System.out.print(" | Vehicle Plate Number: " + car.getCarPlateNumber());
        }
        if (vb instanceof Helicopter heli) {
            System.out.print(" | Helicopter Serial Number: " + heli.getSerialNumber() + " | Helicopter Purpose: " + heli.getPurpose());
        }
        if (vb instanceof Motorcycle moto) {
            System.out.print(" | Motorcycle motor type: " + moto.getMotortype() + " | Motorcycle plate number: " + moto.getMplatenumber());
        }

        System.out.println(); // satır sonu
    }

}
