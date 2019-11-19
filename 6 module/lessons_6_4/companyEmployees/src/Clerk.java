public class Clerk extends AbstractEmployees {
    private static final int MIN_SALARY = 15000;
    private static final int MAX_SALARY = 20000;

    private Clerk(String name, int salary, Company company) {
        super(name, salary, company);
        setSalary(salary);
    }

    public Clerk rename(String newName) {
        return new Clerk(newName, (int) getMonthSalary(), getCompany());
    }

    public static Clerk createCleark(Company company) {
        return new Clerk("Cleark", generateASalary(MIN_SALARY, MAX_SALARY), company);
    }
}