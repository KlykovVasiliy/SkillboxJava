public class SalesMan extends AbstractEmployees {
    private static final int MIN_SALARY = 20000;
    private static final int MAX_SALARY = 30000;
    private final static double PERSENTAGE_OF_SALES = 0.05;
    private int amountSales;
    private int salary = 0;
    private String name;

    private SalesMan(String name, int salary) {
        super(name);
        amountSales = sales();
        salary += (int) (amountSales * PERSENTAGE_OF_SALES);
        this.salary = salary;
    }

    public static SalesMan createSalesMan() {
        return new SalesMan("SalesMan", generateASalary(MIN_SALARY, MAX_SALARY));
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