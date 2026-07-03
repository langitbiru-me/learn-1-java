package week_2.day_3.array_list;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();

        names.add("Alif");
        names.add("Imron");
        names.add("Fattah");

        System.out.println(names);

        // access by index
        System.out.println("nama pada array pertama :" + names.get(0));

        // iterate
        for (String name : names) {
            System.out.println(name);
        }

        names.remove(1);
        names.remove("Alif");

        names.add("Zahra");
        Collections.sort(names);

        // size
        System.out.println("ukuran array: " + names.size());
    }
}
