package model;

import model.enums.VehicleAvailable;
import model.vehicle.Car;

import java.util.Date;

public class Reservation extends BaseModel {

    private User customer; //NOT NULL
    private Car car; //NOT NULL
    private Date start_date; //NOT NULL
    private Date end_date; //NOT NULL
    private VehicleAvailable vehicleAvailable;


    //CONSTRUCTOR
    public Reservation(User customer, Car car, Date start_date, Date end_date, VehicleAvailable vehicleAvailable) {
        this.customer = customer;
        this.car = car;
        this.start_date = start_date;
        this.end_date = end_date;
        this.vehicleAvailable = vehicleAvailable;
    }

    //GETTER/SETTER


    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public VehicleAvailable getCarAvailable() {
        return vehicleAvailable;
    }

    public void setCarAvailable(VehicleAvailable vehicleAvailable) {
        this.vehicleAvailable = vehicleAvailable;
    }
}
