package model.vehicle;

public class Motorcycle extends VehicleBase {

    private String mplatenumber;
    private String motortype;

    public Motorcycle(String mplatenumber, String motortype) {
        this.mplatenumber = mplatenumber;
        this.motortype = motortype;
    }

    public Motorcycle() {

    }


    public String getMplatenumber() {
        return mplatenumber;
    }

    public void setMplatenumber(String mplatenumber) {
        this.mplatenumber = mplatenumber;
    }

    public String getMotortype() {
        return motortype;
    }

    public void setMotortype(String motortype) {
        this.motortype = motortype;
    }
}
