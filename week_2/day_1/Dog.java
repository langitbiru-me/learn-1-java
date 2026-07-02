package week_2.day_1;

// child class - extends animal
public class Dog extends Animal {

    private String breed;

    public Dog(String name, String breed) {
        super(name);    //call parent constructor
        this.breed = breed;
    }

    @Override
    public void speak() {
        System.out.println(name + " Woof woof!");
    }
}
