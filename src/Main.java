import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "transactions.txt";

    public static void main(String[] args) {
        ArrayList<Transaction> transactions = loadFromFile(); // Load data on startup
        Scanner sc = new Scanner(System.in);
        int idCounter = transactions.size() + 1;

        while (true) {
            System.out.println("\n--- PERSONAL FINANCE TRACKER (File Saved) ---");
            System.out.println("1. Add Transaction");
            System.out.println("2. View All Transactions");
            System.out.println("3. Save and Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Description: "); String desc = sc.nextLine();
                System.out.print("Amount: "); double amt = sc.nextDouble(); sc.nextLine();
                System.out.print("Category: "); String cat = sc.nextLine();

                transactions.add(new Transaction(idCounter++, desc, amt, cat));
                System.out.println("✅ Added!");
            } else if (choice == 2) {
                for (Transaction t : transactions) t.display();
            } else if (choice == 3) {
                saveToFile(transactions); // Save before closing
                System.out.println("Data Saved. Goodbye!");
                break;
            }
        }
    }

    // --- RESUME SKILL: FILE WRITING (OUTPUT) ---
    private static void saveToFile(ArrayList<Transaction> list) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Transaction t : list) {
                writer.println(t.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // --- RESUME SKILL: FILE READING (INPUT) ---
    private static ArrayList<Transaction> loadFromFile() {
        ArrayList<Transaction> list = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return list;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                list.add(new Transaction(Integer.parseInt(parts[0]), parts[1],
                        Double.parseDouble(parts[2]), parts[3]));
            }
        } catch (IOException e) {
            System.out.println("Error loading file.");
        }
        return list;
    }
}