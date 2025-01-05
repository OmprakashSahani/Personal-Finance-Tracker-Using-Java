# Personal-Finance-Tracker-Using-Java
A Java-based desktop application to help you track your income and expenses.

Personal Finance Tracker:

A Java-based desktop application to help you track your income and expenses. This app provides an intuitive GUI for managing your financial transactions, generating reports, and exporting data to a CSV file.

Features:

1. Add Transactions: Record income or expense transactions with details like description, amount, category, and date.

2. Edit Transactions: Update existing transaction details directly from the GUI.

3. Search Transactions: Search transactions by description.

4. View Balance: Get a summary of your total income, expenses, and current balance.

5. Export to CSV: Save all transactions to a .csv file for external use.

6. Persistent Storage: Automatically saves all transactions to a file (transactions.dat) and reloads them on app restart.


Getting Started

Prerequisites:

Java Development Kit (JDK): Version 8 or higher.

Integrated Development Environment (IDE): (Optional) BlueJ, IntelliJ IDEA, Eclipse, or similar for editing and running the project.


Installation:

1. Clone the repository or download the source code.


2. Open the project in your favorite IDE or compile the files using the command line.



javac FinanceGUI.java
java FinanceGUI


How to Use:

1. Run the Application:
Execute the FinanceGUI class to launch the application.



2. Adding Transactions:

Enter a description, amount (prefix with + for income and - for expenses), and category in the input fields.

Click Add Transaction to save the record.



3. Viewing Balance:

Click View Balance to view the total income, expenses, and balance.



4. Editing Transactions:

Click Edit Transaction and enter the description of the transaction you wish to edit.

Provide updated details in the dialog boxes.



5. Exporting Transactions:

Click Export to CSV and specify the file name to save your transactions as a .csv file.



6. Searching Transactions:

Click Search by Description and enter a keyword to find matching transactions.




Project Structure:

src  
├── FinanceGUI.java        # Main GUI Application  
├── FinanceManager.java    # Core logic for managing transactions  
├── Transaction.java       # Transaction data model


License:

This project is licensed under the MIT License.

Contributing:

Contributions are welcome! Feel free to fork the repository and submit a pull request.
