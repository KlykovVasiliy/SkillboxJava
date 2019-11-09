public class SalesMan extends AbstractEmployees {
    private static final int MIN_SALARY = 20000;
    private static final int MAX_SALARY = 30000;
    private final static double PERSENTAGE_OF_SALES = 0.05;
    private int amountSales;
    private int salary = 0;
    private String name;

    private SalesMan(String name, int salary) {
        super(name, salary);
        amountSales = sales();
        salary += (int) (amountSales * PERSENTAGE_OF_SALES);
        setSalary(salary);
    }

    public static SalesMan createSalesMan(String name) {
        return new SalesMan(name, generateASalary(MIN_SALARY, MAX_SALARY));
    }

    private static int generateASalary(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    private int sales() {
        int minSale = 50000;
        int maxSale = 300000;
        maxSale -= minSale;
        return (int) (Math.random() * maxSale) + minSale;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSalary(int salary) {
        this.salary = salary;
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