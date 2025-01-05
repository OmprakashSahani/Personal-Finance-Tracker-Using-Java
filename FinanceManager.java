import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class FinanceManager {
    private ArrayList<Transaction> transactions;
    private final String FILE_NAME = "transactions.dat";

    public FinanceManager() {
        transactions = new ArrayList<>();
        loadTransactionsFromFile();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        saveTransactionsToFile();
    }

    public void editTransaction(Transaction transaction, String newDescription, double newAmount, String newCategory) {
        transaction.setDescription(newDescription);
        transaction.setAmount(newAmount);
        transaction.setCategory(newCategory);
        saveTransactionsToFile();
    }

    public void deleteTransaction(Transaction transaction) {
        transactions.remove(transaction);
        saveTransactionsToFile();
    }

    public List<Transaction> searchTransactionsByDescription(String description) {
        return transactions.stream()
                .filter(t -> t.getDescription().toLowerCase().contains(description.toLowerCase()))
                .collect(Collectors.toList());
    }

    public double getTotalIncome() {
        return transactions.stream()
                .filter(Transaction::isIncome)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getTotalExpense() {
        return transactions.stream()
                .filter(t -> !t.isIncome())
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public void exportToCSV(String fileName) {
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            writer.println("Description,Amount,Category,Type,Date");
            for (Transaction transaction : transactions) {
                writer.println(transaction.getDescription() + "," + transaction.getAmount() + ","
                        + transaction.getCategory() + "," + (transaction.isIncome() ? "Income" : "Expense") + ","
                        + transaction.getDate());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // New method to get all transactions
    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions); // Return a copy of the transactions list
    }

    private void saveTransactionsToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(transactions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTransactionsFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            transactions = (ArrayList<Transaction>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
