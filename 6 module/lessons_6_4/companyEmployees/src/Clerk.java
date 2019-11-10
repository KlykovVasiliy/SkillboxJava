public class Clerk extends AbstractEmployees {
    private static final int MIN_SALARY = 15000;
    private static final int MAX_SALARY = 20000;

    private Clerk(String name, int salary) {
        super(name, salary);
        setSalary(salary);
    }

    public static Clerk createCleark() {
        return new Clerk("Cleark", generateASalary(MIN_SALARY, MAX_SALARY));
    }
}