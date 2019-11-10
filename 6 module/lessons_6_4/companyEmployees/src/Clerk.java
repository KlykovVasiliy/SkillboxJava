public class Clerk extends AbstractEmployees {
    private static final int MIN_SALARY = 15000;
    private static final int MAX_SALARY = 20000;
    private int salary;
    private String name;

    private Clerk(String name, int salary) {
        super(name);
        this.salary = salary;
    }

    public static Clerk createCleark() {
        return new Clerk("Cleark", generateASalary(MIN_SALARY, MAX_SALARY));
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