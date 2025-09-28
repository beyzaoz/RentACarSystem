//package model;
//
//import model.enums.VehicleAvailable;
//import model.vehicle.VehicleBase;
//
//import java.sql.Timestamp;
//import java.util.Date;
//
//public class Reservation extends BaseModel {
//
//    private User user; // NOT NULL
//    private VehicleBase vehicle; // Car yerine genel VehicleBase
//    private Date start_date; // NOT NULL
//    private Date end_date; // NOT NULL
//    private VehicleAvailable vehicleAvailable;
//
//    // Constructor
//    public Reservation(User user, VehicleBase vehicle, Date start_date, Date end_date, VehicleAvailable vehicleAvailable) {
//        this.user = user;
//        this.vehicle = vehicle;
//        this.start_date = start_date;
//        this.end_date = end_date;
//        this.vehicleAvailable = vehicleAvailable;
//    }
//
//    public Reservation(User user, VehicleBase vb, Timestamp startDate, Timestamp endDate, VehicleAvailable available) {
//        super();
//    }
//
//    // GETTER / SETTER
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public VehicleBase getVehicle() {
//        return vehicle;
//    }
//
//    public void setVehicle(VehicleBase vehicle) {
//        this.vehicle = vehicle;
//    }
//
//    public Date getStart_date() {
//        return start_date;
//    }
//
//    public void setStart_date(Date start_date) {
//        this.start_date = start_date;
//    }
//
//    public Date getEnd_date() {
//        return end_date;
//    }
//
//    public void setEnd_date(Date end_date) {
//        this.end_date = end_date;
//    }
//
//    public VehicleAvailable getVehicleAvailable() {
//        return vehicleAvailable;
//    }
//
//    public void setVehicleAvailable(VehicleAvailable vehicleAvailable) {
//        this.vehicleAvailable = vehicleAvailable;
//    }
//}
