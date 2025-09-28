package UI;
import dao.VehicleDao;
import exception.ExceptionMessages;
import exception.SystemException;
import model.User;
import model.enums.VehicleAvailable;
import model.vehicle.Car;
import model.vehicle.Helicopter;
import model.vehicle.Motorcycle;
import model.vehicle.VehicleBase;
import service.VehicleService;
import java.util.List;
import java.util.Scanner;

public class AdminLogIn {

    public static void adminLogInPanel(Scanner s, User user){

        try {
            while(true){
                System.out.println("WELCOME " + user.getFirst_name().toUpperCase());
                System.out.println("1-Add Vehicle");
                System.out.println("2-Update Vehicle");
                System.out.println("3-Delete Vehicle");
                System.out.println("4-List Vehicles");
                System.out.println("5-Exit");
                System.out.println("Please select one option: ");

                String option = s.nextLine();

                switch(option){
                    case "1":
                        addVehicle(s,user);
                        break;
                    case "2":
                        updateVehicle(s,user);
                        break;
                    case "3":
                        deleteVehicle(s,user);
                        break;
                    case "4":
                        listVehicle(s,user);
                        break;
                    case "5":
                        StartPage.startingPage();
                        break;
                    default:
                        System.out.println("Invalid option!");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    private static void listVehicle(Scanner s, User user) throws SystemException {

            System.out.println("Listing...");

            VehicleDao vehicleDao = new VehicleDao();
            VehicleBase vb = new VehicleBase();
            VehicleService vehicleService = new VehicleService(vehicleDao);

                    int page = 0;
                    vehicleService.vehicleListed(vb,user); //todo
                    vehicleDao.allListed(vb, user.getId(),page );
                    List<VehicleBase> vehicles = vehicleDao.allListed(vb, user.getId(), page);
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

    private static void deleteVehicle(Scanner s, User user) throws SystemException {

            System.out.println("Please enter vehicle ID: ");
            long vehicleid = s.nextLong();
            s.nextLine();
            if(vehicleid==0){
                System.out.println("No vehicle found with ID:" + vehicleid);
                return;
            }


            System.out.println("ARE YOU SURE WANT TO DELETE IT? YES/NO ");
            String confirm = s.nextLine();
            switch(confirm.toLowerCase().trim()){
                case "yes":
                    VehicleDao vehicleDao = new VehicleDao();
                    VehicleBase vb = null;

                    try {
                        vb = vehicleDao.findById(vehicleid);
                        if(vb==null){
                            System.out.println("No vehicle found with ID think: " + vehicleid);
                            return;
                        }
                    } catch (SystemException e) {
                        System.out.println("ID is not exist!");
                    }

                    VehicleService vs = new VehicleService(vehicleDao);
                    try {
                        vs.vehicleDelete(vb,user);
                    } catch (SystemException e) {
                        System.out.println("Error deleting vehicle!!");
                    }
                    System.out.println("Successfully deleted!!");
                    break;
                case "no":
                    System.out.println("Delete Canceled!");
                    break;
                default:
                    System.out.println("Invalid choise.");
                    break;


            }
    }

    private static void updateVehicle(Scanner s, User user) throws SystemException {
        try {
            System.out.println("Please enter vehicle ID: ");
            long vehicleid;
            VehicleBase vb = null;
            VehicleDao vehicleDao = new VehicleDao();

            //ID CHECK-----
            while(true){
                vehicleid = s.nextLong();
                vb = vehicleDao.findById(vehicleid);

                if(vb!= null || vb.getId() != 0){
                    break;
                }else{
                    System.out.println("No vehicle found with this ID.");
                }
            }
            //------------


                System.out.println("Enter new value (0 for no change): ");
                System.out.println("Hourly Price: ");
                double hourlyp = s.nextDouble();
                System.out.println("Daily Price: ");
                double dailyp = s.nextDouble();
                System.out.println("Weekly Price: ");
                double weeklyp = s.nextDouble();
                System.out.println("Monthly Price: ");
                double monthlyp = s.nextDouble();
                s.nextLine();

                System.out.println("Vehicle Availability (AVAILABLE/NOTAVAILABLE):");
                String availability = s.nextLine().trim().toUpperCase();

                if (hourlyp != 0) vb.setHourlyPrice(hourlyp);
                if (dailyp != 0) vb.setDailyPrice(dailyp);
                if (weeklyp != 0) vb.setWeeklyPrice(weeklyp);
                if (monthlyp != 0) vb.setMonthlyPrice(monthlyp);
                if (!availability.equals("0")) {
                    try {
                        vb.setAvailable(VehicleAvailable.valueOf(availability.toUpperCase()));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid value! Only AVAILABLE or NOTAVAILABLE can be entered.");
                    }
                }

                switch (vb.getVehicleType().toLowerCase()) {
                    case "car" -> {
                        Car car = (Car) vb;
                        System.out.print("Car Plate Number: ");
                        String plate = s.nextLine();
                        if (!plate.isBlank()) car.setCarPlateNumber(plate);
                    }
                    case "helicopter" -> {
                        Helicopter heli = (Helicopter) vb;
                        System.out.print("Serial Number: ");
                        String serial = s.nextLine();
                        if (!serial.isBlank()) heli.setSerialNumber(serial);

                        System.out.print("Purpose: ");
                        String purpose = s.nextLine();
                        if (!purpose.isBlank()) heli.setPurpose(purpose);
                    }
                    case "motorcycle" -> {
                        Motorcycle moto = (Motorcycle) vb;
                        System.out.print("Motor Type: ");
                        String motortype = s.nextLine();
                        if (!motortype.isBlank()) moto.setMotortype(motortype);

                        System.out.print("M Plate Number: ");
                        String mplatenum = s.nextLine();
                        if (!mplatenum.isBlank()) moto.setMplatenumber(mplatenum);
                    }
                }
                VehicleService vehicleService = new VehicleService(vehicleDao);
                vehicleService.vehicleUpdate(vb, user);
                System.out.println("Successfully Updated!");


        } catch (SystemException e) {
            throw new SystemException(ExceptionMessages.INVALID_UPDATE);
        }
    }

    private static void addVehicle(Scanner s, User user) throws SystemException {

        VehicleDao vehicleDao = new VehicleDao();
        VehicleService vehicleService = new VehicleService(vehicleDao);

        // String girişleri için yardımcı method
        String vehicleModel = readNonEmptyString(s, "Enter the vehicle model: ");
        String vehicleType = readNonEmptyString(s, "Enter the vehicle type (Car/Helicopter/Motorcycle): ").toLowerCase();

        // Double girişleri için yardımcı method
        double hourlyPrice = readPositiveDouble(s, "Enter the Hourly Price: ");
        double dailyPrice = readPositiveDouble(s, "Enter the Daily Price: ");
        double weeklyPrice = readPositiveDouble(s, "Enter the Weekly Price: ");
        double monthlyPrice = readPositiveDouble(s, "Enter the Monthly Price: ");
        double vehiclePrice = readPositiveDouble(s, "Enter the Vehicle Price: ");

        switch(vehicleType) {
            case "car" -> {
                String carPlate = readNonEmptyString(s, "Enter car Plate: ");

                Car car = new Car();
                car.setVehiclemodel(vehicleModel);
                car.setVehicleType(vehicleType);
                car.setHourlyPrice(hourlyPrice);
                car.setDailyPrice(dailyPrice);
                car.setWeeklyPrice(weeklyPrice);
                car.setMonthlyPrice(monthlyPrice);
                car.setCarPlateNumber(carPlate);
                car.setAvailable(VehicleAvailable.AVAILABLE);

                vehicleService.vehicleSave(car,user);
            }
            case "helicopter" -> {
                String helicopterSerialNumber = readNonEmptyString(s, "Enter helicopter Serial Number: ");
                String helicopterPurpose = readNonEmptyString(s, "Enter helicopter Purpose (Transportation or Private use): ").toLowerCase();

                Helicopter helicopter = new Helicopter();
                helicopter.setVehiclemodel(vehicleModel);
                helicopter.setVehicleType(vehicleType);
                helicopter.setHourlyPrice(hourlyPrice);
                helicopter.setDailyPrice(dailyPrice);
                helicopter.setWeeklyPrice(weeklyPrice);
                helicopter.setMonthlyPrice(monthlyPrice);
                helicopter.setSerialNumber(helicopterSerialNumber);
                helicopter.setAvailable(VehicleAvailable.AVAILABLE);
                helicopter.setPurpose(helicopterPurpose);

                vehicleService.vehicleSave(helicopter,user);
            }
            case "motorcycle" -> {
                String motorVehicleType = readNonEmptyString(s, "Enter the motor Type: ");
                String plateNumber = readNonEmptyString(s, "Enter the motor Plate Number: ");

                Motorcycle motorcycle = new Motorcycle();
                motorcycle.setVehiclemodel(vehicleModel);
                motorcycle.setVehicleType(vehicleType);
                motorcycle.setHourlyPrice(hourlyPrice);
                motorcycle.setDailyPrice(dailyPrice);
                motorcycle.setWeeklyPrice(weeklyPrice);
                motorcycle.setMonthlyPrice(monthlyPrice);
                motorcycle.setMotortype(motorVehicleType);
                motorcycle.setMplatenumber(plateNumber);
                motorcycle.setAvailable(VehicleAvailable.AVAILABLE);

                vehicleService.vehicleSave(motorcycle,user);
            }
            default -> System.out.println("Unknown vehicle type. Please enter Car, Helicopter, or Motorcycle.");
        }
    }

    private static String readNonEmptyString(Scanner s, String message) {
        String input;
        do {
            System.out.print(message);
            input = s.nextLine().trim();
            if(input.isEmpty()) {
                System.out.println("Value cannot be empty. Please try again.");
            }
        } while(input.isEmpty());
        return input;
    }

    private static double readPositiveDouble(Scanner s, String message) {
        double value = -1;
        do {
            System.out.print(message);
            if(s.hasNextDouble()) {
                value = s.nextDouble();
                if(value < 0) {
                    System.out.println("Value must be positive. Try again.");
                    value = -1;
                }
            } else {
                System.out.println("Invalid number. Try again.");
                s.next(); // hatalı girişi at
            }
        } while(value < 0);
        s.nextLine(); // Scanner buffer temizleme
        return value;
    }
}


