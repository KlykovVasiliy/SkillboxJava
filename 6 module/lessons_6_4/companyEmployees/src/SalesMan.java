public class SalesMan extends AbstractEmployees {
    private int amountSales;
    private double salary;
    private final static double PERSENTAGE_OF_SALES = 0.05;


    public SalesMan(String name) {
        super(name);
        amountSales = sales();
        salary = amountSales * PERSENTAGE_OF_SALES;
    }

    public double getSales() {
        return amountSales;
    }

    @Override
    public void setSalary(double salary) {
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

    @Override
    public double getEmployeeRevenue() {
        return amountSales;
    }
}