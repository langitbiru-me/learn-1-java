package week_2.day_1;

public abstract class Shape {

    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    public abstract double area();

    public void describe() {
        System.out.println("This is a " + color + " shape." + "shape with area :" + area());
    }
}
