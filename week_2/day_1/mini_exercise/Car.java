package week_2.day_1.mini_exercise;

public class Car extends Vehicle implements Electric {

    public Car(String make, String model, int year) {
        super(make, model, year);
    }

    @Override
    public void fuelType() {
        System.out.println("fuel type: " + this.getClass().getSimpleName());
    }

    @Override
    public int batteryLevel() {
        System.out.println("battery level");
        return 0;
    }

}
