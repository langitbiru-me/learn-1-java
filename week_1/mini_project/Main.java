package week_1.mini_project;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
       ExpenseManager manager = new ExpenseManager();
       manager.loadFromFile(); // Load existing expenses from file

       Scanner scanner = new Scanner(System.in);
       System.out.println("💰 Expense Tracker — type 'quit' to save and exit");

       while (true){
        System.out.print("> ");
        String input = scanner.nextLine().trim();

        if (input.equals("add")){
            String[] parts = input.split(" ");
            String name = parts[1];
            double amount = Double.parseDouble(parts[2]);
            manager.addExpense(name, amount);

        } else if (input.equals("list")){
            manager.listAll();

        } else if (input.equals("total")){
            System.out.println("Total Expense: " + manager.totalExpense());

        } else if (input.equals("quit")){
            manager.saveToFile(); // Save expenses to file before exiting
            System.out.println("Expenses saved. Exiting...");
            break;

        } else {
            System.out.println("Unknown command. Use 'add', 'list', 'total', or 'quit'.");
        }
       }
    }
}
