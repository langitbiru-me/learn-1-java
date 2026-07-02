package week_2.day_1;

public class Cat extends Animal {

    public Cat(String name) {
        super(name);
    }

    @Override
    public void speak() {
        System.out.println(name + " Meow meow!");
    }
}
