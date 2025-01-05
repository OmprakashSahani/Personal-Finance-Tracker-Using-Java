import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FinanceGUI extends JFrame {
    private FinanceManager financeManager;
    private JTextArea transactionArea;
    private JTextField descriptionField, amountField, categoryField;

    public FinanceGUI() {
        financeManager = new FinanceManager();
        setTitle("Personal Finance Tracker");
        setLayout(new BorderLayout());

        // Create UI components
        descriptionField = new JTextField(20);
        amountField = new JTextField(10);
        categoryField = new JTextField(15);
        transactionArea = new JTextArea(10, 30);
        transactionArea.setEditable(false);

        JButton addTransactionButton = new JButton("Add Transaction");
        JButton viewBalanceButton = new JButton("View Balance");
        JButton exportButton = new JButton("Export to CSV");
        JButton searchButton = new JButton("Search by Description");
        JButton editTransactionButton = new JButton("Edit Transaction");

        // Set up the layout
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Amount:"));
        inputPanel.add(amountField);
        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryField);
        inputPanel.add(addTransactionButton);

        JPanel reportPanel = new JPanel();
        reportPanel.add(viewBalanceButton);
        reportPanel.add(exportButton);
        reportPanel.add(searchButton);
        reportPanel.add(editTransactionButton);
        reportPanel.add(new JScrollPane(transactionArea));

        add(inputPanel, BorderLayout.NORTH);
        add(reportPanel, BorderLayout.CENTER);

        // Add action listeners for buttons
        addTransactionButton.addActionListener(e -> addTransaction());
        viewBalanceButton.addActionListener(e -> viewBalance());
        exportButton.addActionListener(e -> exportToCSV());
        searchButton.addActionListener(e -> searchTransaction());
        editTransactionButton.addActionListener(e -> editTransaction());

        // Display the frame
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addTransaction() {
        String description = descriptionField.getText();
        double amount;
        String category = categoryField.getText();
        boolean isIncome = amountField.getText().startsWith("+");

        try {
            amount = Double.parseDouble(amountField.getText().replaceAll("[^\\d.]", ""));
            Transaction transaction = new Transaction(description, amount, category, isIncome, new java.util.Date());
            financeManager.addTransaction(transaction);
            updateTransactionList();
            descriptionField.setText("");
            amountField.setText("");
            categoryField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount format.");
        }
    }

    private void viewBalance() {
        double totalIncome = financeManager.getTotalIncome();
        double totalExpense = financeManager.getTotalExpense();
        double balance = totalIncome - totalExpense;
        JOptionPane.showMessageDialog(this, "Total Balance: $" + balance + "\nTotal Income: $" + totalIncome + "\nTotal Expenses: $" + totalExpense);
    }

    private void exportToCSV() {
        String fileName = JOptionPane.showInputDialog(this, "Enter the filename (with .csv extension):");
        if (fileName != null && !fileName.isEmpty()) {
            financeManager.exportToCSV(fileName);
            JOptionPane.showMessageDialog(this, "Data exported to " + fileName);
        }
    }

    private void searchTransaction() {
        String description = JOptionPane.showInputDialog(this, "Enter description to search:");
        List<Transaction> results = financeManager.searchTransactionsByDescription(description);
        transactionArea.setText("");
        for (Transaction t : results) {
            transactionArea.append(t.toString() + "\n");
        }
    }

    private void editTransaction() {
        String description = JOptionPane.showInputDialog(this, "Enter the description of the transaction to edit:");
        List<Transaction> results = financeManager.searchTransactionsByDescription(description);

        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Transaction not found.");
        } else {
            Transaction transactionToEdit = results.get(0); // Let's edit the first match
            String newDescription = JOptionPane.showInputDialog(this, "Enter new description:", transactionToEdit.getDescription());
            double newAmount = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter new amount:", transactionToEdit.getAmount()));
            String newCategory = JOptionPane.showInputDialog(this, "Enter new category:", transactionToEdit.getCategory());
            financeManager.editTransaction(transactionToEdit, newDescription, newAmount, newCategory);
            updateTransactionList();
        }
    }

    private void updateTransactionList() {
        transactionArea.setText("");
        for (Transaction transaction : financeManager.getTransactions()) {
            transactionArea.append(transaction.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        new FinanceGUI();
    }
}
