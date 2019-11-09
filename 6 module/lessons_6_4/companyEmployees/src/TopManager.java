public class TopManager extends AbstractEmployees {
    private static final int MIN_SALARY = 30000;
    private static final int MAX_SALARY = 40000;
    private int salary;
    private String name;

    private TopManager(String name, int salary) {
        super(name, salary);
        setSalary(salary);
    }

    public static TopManager createTopManager(String name) {
        return new TopManager(name, generateASalary(MIN_SALARY, MAX_SALARY));
    }

    private static int generateASalary(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    protected void giveAPremia(double money) {
        salary += money;
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
        return 0;
    }
}