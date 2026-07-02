package week_2.day_1;

public class Main {
    public static void main(String[] args) {
       BankAccount account = new BankAccount("John Doe", 1000.0);
       account.deposit(500.0);
       account.withdraw(200.0);
       System.out.println(account);


    System.out.println("Animal Testing:");
    Animal[] animals = {
        new Dog("Rex", "Labrador"),
        new Cat("Mimi"),
        new Dog("Buddy", "Poodle"),
    };

    for (Animal animal : animals) {
        animal.speak();
    }
}
}