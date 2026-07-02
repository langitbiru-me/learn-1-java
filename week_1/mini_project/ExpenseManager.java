package week_1.mini_project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {
    private List<Expense> expenses = new ArrayList<>();

    public void addExpense(String name, double amount) {
        int id = expenses.size() + 1; // Simple ID generation
        String date = LocalDate.now().toString(); // Get current date
        expenses.add(new Expense(id, name, amount, date));
        System.out.println("Expense added: " + name + ", Amount: " + amount + ", Date: " + date);
    }

    public void listAll(){
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
        } else {
            for (Expense e : expenses) {
                System.out.println(e);
            }
        }
    }

    public double totalExpense() {
       return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    public void loadFromFile(){
        this.expenses = FileStorage.loadFile();
    }

    public void saveToFile(){
        FileStorage.saveFile(expenses);
    }
}
