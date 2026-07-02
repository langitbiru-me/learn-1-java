import java.util.Arrays; // Import untuk menampilkan array agar terbaca

public class Main {

    // Variabel class (Static agar bisa diakses langsung di main)
    static int age = 25;
    static double price = 20.22;
    static boolean isActive = true;
    static char grade = 'a';
    static String name = "Alif";
    static int[] scores = {50, 48, 56};

    // method
    public static int add(int a, int b) {
        return a + b;
    }

    // task 2 : handle conditions
    public static void checkAge(int age) {
        if (age < 18) {
            System.out.println("You are a minor.");
        } else if (age >= 18 && age < 65) {
            System.out.println("You are an adult.");
        } else {
            System.out.println("You are a senior citizen.");
        }
    }

    // use switch case
    public static void checkGrade(char grade) {
        switch (grade) {
            case 'A' -> System.out.println("Excellent!");
            case 'B' -> System.out.println("Good job!");
            default -> System.out.println("Keep trying!");
        }
    }

    public static void loopExample(){
        for (int i=0; i < 5; i++){
            System.out.println("Loop iteration: " + i);
        }
    }

    // use for:each loop
    public static void printScores(int[] scores) {
        for (int sc : scores) {
            System.out.println("Score: " + sc);
        }
    }

    public static void main(String[] args) { // Perhatikan 'String[]' (S besar)
        System.out.println(age);
        System.out.println(price);
        System.out.println(isActive);
        System.out.println(grade);
        System.out.println(name);
        
        // Di Java, print array langsung akan menampilkan memory address. 
        // Gunakan Arrays.toString() untuk melihat isinya.
        System.out.println(Arrays.toString(scores)); 
        
        System.out.println(add(10, 20));
    }
}