package model.vehicle;

public class Car  extends VehicleBase {
    private String CarPlateNumber;//NOT NULL

    public Car() {

    }


    public String getCarPlateNumber() {
        return CarPlateNumber;
    }

    public void setCarPlateNumber(String carPlateNumber) {
        CarPlateNumber = carPlateNumber;
    }

    public Car(String carPlateNumber) {
        CarPlateNumber = carPlateNumber;
    }

    @Override
    public String toString() {
        return "Car{" +
                "CarPlateNumber='" + CarPlateNumber + '\'' +
                '}';
    }


}
