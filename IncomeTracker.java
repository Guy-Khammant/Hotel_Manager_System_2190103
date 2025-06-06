public class IncomeTracker implements Observer {
    private double totalIncome = 0;

    public void update(double amount) {
        totalIncome += amount;
        System.out.println("Income updated: $" + amount + ", Total: $" + totalIncome + '\n' + "------------------------------------");
    }

    public double getTotalIncome() { 
        return totalIncome; 
    }
}
