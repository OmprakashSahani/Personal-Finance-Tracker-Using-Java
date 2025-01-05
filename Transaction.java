import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
    private String description;
    private double amount;
    private String category;
    private boolean isIncome;
    private Date date;

    public Transaction(String description, double amount, String category, boolean isIncome, Date date) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.isIncome = isIncome;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public boolean isIncome() {
        return isIncome;
    }

    public Date getDate() {
        return date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return (isIncome ? "Income" : "Expense") + ": " + description + " - $" + amount + " (" + category + ") on " + date.toString();
    }
}
