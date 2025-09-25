package UI;

import dao.VehicleDao;
import exception.SystemException;
import model.User;
import model.vehicle.Car;
import model.vehicle.Helicopter;
import model.vehicle.Motorcycle;
import model.vehicle.VehicleBase;
import service.VehicleService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CustomerLogin {

    public static void customerLoginPage(Scanner s, User user) throws SystemException {
        System.out.println("WELCOME " + user.getFirst_name().toUpperCase());
        System.out.println("Please select one option: ");
        System.out.println("1-Search Vehicle");
        System.out.println("2-Rent A Vehicle");
        System.out.println("3-Cancelations");
        System.out.println("4-Exit");
        String option = s.nextLine();

        switch (option){
            case "1":
                searchVehicle(s,user);
                break;
            case "2":
                rentVehicle(user); //todo
                break;
            case "3":
                cancelation(); //todo
                break;
            case "4":
                break;
        }

    }

    private static void cancelation() {
    }

    private static void rentVehicle(User user) throws SystemException {
        Scanner s = new Scanner(System.in);
        System.out.println("Listing...");
        allListedVehicle(user);

        System.out.println("Please Choose a car and type the car ID: ");
        long id = s.nextLong();
        System.out.println("Rent Hourly/Daily/Weekly/Monthly:  ");
        System.out.println("Please Choose a pickup date and time: ");
        LocalDateTime pickupdate = LocalDateTime.parse(s.next());
        System.out.println("Please Choose a drop of date and time: ");
        LocalDateTime dropofdate = LocalDateTime.parse(s.next());



    }
    private static void allListedVehicle(User user) throws SystemException {
        System.out.println("Listing...");

        VehicleDao vehicleDao = new VehicleDao();
        VehicleBase vb = new VehicleBase();
        VehicleService vehicleService = new VehicleService(vehicleDao);

        int page= 1 ;
        vehicleService.vehicleListed(vb,user);
        vehicleDao.allListed(vb, user.getId(),page);
        List<VehicleBase> vehicles = vehicleDao.allListed(vb, user.getId(),page);
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            for (VehicleBase t : vehicles) {
                printVehicleInfo(t);
            }
        }
    }
    private static void printVehicleInfo(VehicleBase vb){
        System.out.print(vb.getId() + " | " + vb.getVehicleType() + " | " + vb.getVehiclemodel() +
                " | Hourly: " + vb.getHourlyPrice() +
                " | Daily: " + vb.getDailyPrice() +
                " | Weekly: " + vb.getWeeklyPrice() +
                " | Monthly: " + vb.getMonthlyPrice() +
                " | Availability: " + vb.getAvailable());

        if (vb instanceof Car car) {
            System.out.print(" | Vehicle Plate Number: " + car.getCarPlateNumber());
        }
        if (vb instanceof Helicopter heli) {
            System.out.print(" | Helicopter Serial Number: " + heli.getSerialNumber() +
                    " | Helicopter Purpose: " + heli.getPurpose());
        }
        if (vb instanceof Motorcycle moto) {
            System.out.print(" | Motorcycle motor type: " + moto.getMotortype() +
                    " | Motorcycle plate number: " + moto.getMplatenumber());
        }

        System.out.println(); // satır sonu
    }

    private static void searchVehicle(Scanner s, User user) throws SystemException {

        while(true){
        System.out.println("Search By: ");
        System.out.println("1-ID ");
        System.out.println("2-Vehicle Type ");
        System.out.println("3-Exit ");
        System.out.println("Your choise: ");
        String choise = s.nextLine();


        VehicleDao vehicleDao = new VehicleDao();
        VehicleService vehicleService = new VehicleService(vehicleDao);

        switch (choise){
            case "1":

                try {
                    long vehicleid;

                    //ID CHECK-----
                    while (true) {
                        System.out.println("Please enter vehicle ID: ");
                        vehicleid = s.nextLong();
                        s.nextLine();
                        VehicleBase vb = vehicleService.vehicleSearchById(vehicleid, user);
                        if (vb != null && vb.getId() != 0) {
                            System.out.println("Searching...");
                            Thread.sleep(3000); //3 second
                            printVehicleInfoCustomer(vb);

                        }else{
                            System.out.println("It is null");
                        }
                        break;

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;


            case "2":
                try{
                    String vehicletype = "";
                    //Type check
                    while(true){
                        System.out.println("Please enter Vehicle type(Car/Helicopter/Motorcycle)");
                        vehicletype = s.nextLine().toUpperCase();
                        List<VehicleBase> vb = vehicleService.vehicleSearchByType(vehicletype,user);

                        if (vb != null && !vb.isEmpty()) {
                            System.out.println("Searching...");
                            Thread.sleep(3000); //3 second
                            for(VehicleBase t : vb){
                            printVehicleInfoCustomer(t);
                            }
                        }else{
                            System.out.println("It is null");
                        }
                        break;

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "3":
                break;

            default:

                System.out.println("Invalid Option. Please try again!");

        }

    }
    }

    private static void printVehicleInfoCustomer(VehicleBase vb){
        System.out.print(vb.getId() + " | " + vb.getVehicleType() + " | " + vb.getVehiclemodel() +
                " | Hourly: " + vb.getHourlyPrice() +
                " | Daily: " + vb.getDailyPrice() +
                " | Weekly: " + vb.getWeeklyPrice() +
                " | Monthly: " + vb.getMonthlyPrice() +
                " | Availability: " + vb.getAvailable());

        if (vb instanceof Car car) {
            System.out.print(" | Vehicle Plate Number: " + car.getCarPlateNumber());
        }
        if (vb instanceof Helicopter heli) {
            System.out.print(" | Helicopter Serial Number: " + heli.getSerialNumber() +
                    " | Helicopter Purpose: " + heli.getPurpose());
        }
        if (vb instanceof Motorcycle moto) {
            System.out.print(" | Motorcycle motor type: " + moto.getMotortype() +
                    " | Motorcycle plate number: " + moto.getMplatenumber());
        }

        System.out.println(); // satır sonu
    }

}
