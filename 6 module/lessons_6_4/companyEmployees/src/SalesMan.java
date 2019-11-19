public class SalesMan extends AbstractEmployees {
    private static final int MIN_SALARY = 20000;
    private static final int MAX_SALARY = 30000;
    private static final double PERSENTAGE_OF_SALES = 0.05;
    private int amountSales;

    private SalesMan(String name, int salary, Company company) {
        super(name, salary, company);
        amountSales = sales();
        salary += (int) (amountSales * PERSENTAGE_OF_SALES);
        setSalary(salary);
    }

    public SalesMan rename(String newName) {
        return new SalesMan(newName, getMonthSalary(), getCompany());
    }

    public static SalesMan createSalesMan(Company company) {
        return new SalesMan("SalesMan", generateASalary(MIN_SALARY, MAX_SALARY), company);
    }

    private int sales() {
        int minSale = 50000;
        int maxSale = 300000;
        maxSale -= minSale;
        return (int) (Math.random() * maxSale) + minSale;
    }

    @Override
    public double getEmployeeRevenue() {
        return amountSales;
    }
}