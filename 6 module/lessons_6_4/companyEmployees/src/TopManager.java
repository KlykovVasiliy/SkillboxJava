public class TopManager extends AbstractEmployees {
    private static final int MIN_SALARY = 30000;
    private static final int MAX_SALARY = 40000;
    private final int salaryWithoutPremia;

    private TopManager(String name, int salary) {
        super(name, salary);
        salaryWithoutPremia = salary;
        setSalary(salary);
    }

    public static TopManager createTopManager() {
        return new TopManager("TopManager", generateASalary(MIN_SALARY, MAX_SALARY));
    }

    protected void giveAPremia(double money) {
        if (isPremiaNotBeen()) {
            setSalary((int) (getMonthSalary() + money));
        }
    }

    private boolean isPremiaNotBeen() {
        return getMonthSalary() == salaryWithoutPremia;
    }
}