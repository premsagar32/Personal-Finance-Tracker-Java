import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private String description;
    private double amount;
    private String category;

    public Transaction(int id, String description, double amount, String category) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    // This converts the object into a simple string for the text file
    public String toFileString() {
        return id + "," + description + "," + amount + "," + category;
    }

    public void display() {
        System.out.printf("ID: %d | %-15s | ₹%-10.2f | %s%n", id, description, amount, category);
    }
}