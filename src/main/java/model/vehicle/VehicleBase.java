package model.vehicle;

import model.BaseModel;
import model.enums.VehicleAvailable;

public class VehicleBase extends BaseModel {
    private double hourlyPrice;
    private double dailyPrice;
    private double weeklyPrice;
    private double monthlyPrice;
    private VehicleAvailable available;
    private String vehicleType;
    private String vehiclebrand;
    private double vehiclePrice;


    public VehicleBase(double hourlyPrice, double dailyPrice, double weeklyPrice, double monthlyPrice, VehicleAvailable available, String vehicleType, String vehiclemodel,double vehiclePrice) {
        this.hourlyPrice = hourlyPrice;
        this.dailyPrice = dailyPrice;
        this.weeklyPrice = weeklyPrice;
        this.monthlyPrice = monthlyPrice;
        this.available = VehicleAvailable.AVAILABLE;
        this.vehicleType = vehicleType;
        this.vehiclebrand = vehiclemodel;
        this.vehiclePrice = vehiclePrice;
    }

    public String getVehiclebrand() {
        return vehiclebrand;
    }

    public void setVehiclebrand(String vehiclebrand) {
        this.vehiclebrand = vehiclebrand;
    }

    public double getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(double vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    public VehicleBase() {
    }

    public double getHourlyPrice() {
        return hourlyPrice;
    }

    public void setHourlyPrice(double hourlyPrice) {
        this.hourlyPrice = hourlyPrice;
    }

    public double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public double getWeeklyPrice() {
        return weeklyPrice;
    }

    public void setWeeklyPrice(double weeklyPrice) {
        this.weeklyPrice = weeklyPrice;
    }

    public double getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(double monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    public VehicleAvailable getAvailable() {
        return available;
    }

    public void setAvailable(VehicleAvailable available) {
        this.available = available;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehiclemodel() {
        return vehiclebrand;
    }

    public void setVehiclemodel(String vehiclemodel) {
        this.vehiclebrand = vehiclemodel;
    }

    @Override
    public String toString() {
        return "hourlyPrice=" + hourlyPrice +
                "|| dailyPrice=" + dailyPrice +
                "|| weeklyPrice=" + weeklyPrice +
                "|| monthlyPrice=" + monthlyPrice +
                "|| available=" + available +
                "|| vehicleType='" + vehicleType +
                "|| vehiclebrand='" + vehiclebrand +
                "|| vehiclePrice=" + vehiclePrice
               ;
    }
}
