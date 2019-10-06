package Company;

public class SalesMan extends AbstractEmployees {
    private int amountSales = sales();
    private double salary = 0.0;

    public SalesMan(String name) {
        super(name);
        increaseIncomeCompany(amountSales);                             //не происходит пополнение бюджета компании
        salary = amountSales * PERSENTAGE_OF_INCOME_COMPANY;
    }

    protected void setSalary(double salary) {
        this.salary += salary;
    }

    private int sales() {
        int minSale = 50000;
        int maxSale = 300000;
        maxSale -= minSale;
        return (int) (Math.random() * maxSale) + minSale;
    }

    @Override
    public double getMonthSalary() {
        return salary;
    }
}