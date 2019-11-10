public class TopManager extends AbstractEmployees {
    private static final int MIN_SALARY = 30000;
    private static final int MAX_SALARY = 40000;
    private  int salary;
    private final int salaryWithoutPremia;
    private String name;

    private TopManager(String name, int salary) {
        super(name);
        salaryWithoutPremia = salary;
        this.salary = salary;
    }

    public static TopManager createTopManager() {
        return new TopManager("TopManager", generateASalary(MIN_SALARY, MAX_SALARY));
    }

    protected void giveAPremia(double money) {
        if (isPremiaNotBeen()) {
            salary += money;
        }
    }

    private boolean isPremiaNotBeen() {
        return salary == salaryWithoutPremia;
    }

    @Override
    public double getMonthSalary() {
        return salary;
    }

    @Override
    public double getEmployeeRevenue() {
        return 0;
    }
}