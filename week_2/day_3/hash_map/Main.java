package week_2.day_3.hash_map;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        HashMap<String, Integer> scores = new HashMap<>();

        scores.put("Alif", 100);
        scores.put("Imron", 90);
        scores.put("Fattah", 80);

        System.out.println(scores);

        // access value by key
        System.out.println("score of alif: " + scores.get("Alif"));

        // more safe
        System.out.println("score of ali: " + scores.getOrDefault("Ali", 0));

        // iterate
        for (String name : scores.keySet()) {
            System.out.println(name + ":" + scores.get(name));
        }

        // update value
        scores.put("Alif", 100);
        System.out.println(scores);

        // remove
        scores.remove("Imron");
        System.out.println(scores);

        // iterate
        for (String name : scores.keySet()) {
            System.out.println(name + ":" + scores.get(name));
        }
    }

}
