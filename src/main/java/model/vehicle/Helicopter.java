package model.vehicle;

public class Helicopter extends VehicleBase {

    private String serialNumber;
    private String purpose;

    public Helicopter(String serialNumber, String purpose) {
        this.serialNumber = serialNumber;
        this.purpose = purpose;
    }

    public Helicopter() {

    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
