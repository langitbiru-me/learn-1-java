package week_1.mini_project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
    private static final String FILE_NAME = "expences.txt";

    public static void saveFile (List<Expense> expenses){
    try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
           for (Expense e : expenses){
            writer.println(e.getID() + ", "+e.getName() + ", "+e.getAmount() + ", "+e.getDate());
           }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static List<Expense> loadFile(){
        List<Expense> expenses = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return expenses; // Return empty list if file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                expenses.add(new Expense(Integer.parseInt(parts[0]), parts[1], Double.parseDouble(parts[2]), parts[3]));

            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

        return expenses;
    }
}
