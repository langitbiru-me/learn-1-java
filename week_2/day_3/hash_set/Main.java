package week_2.day_3.hash_set;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        HashSet<String> names = new HashSet<>();

        names.add("Alif");
        names.add("Imron");
        names.add("Fattah");
        names.add("Alif");
        names.add("Hafizh");

        System.out.println(names);

        // iterate
        for (String name : names) {
            System.out.println(name);
        }

        // remove
        names.remove("Imron");
        System.out.println(names);

        // iterate
        for (String name : names) {
            System.out.println(name);
        }
    }
}
