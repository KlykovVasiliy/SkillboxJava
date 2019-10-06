package Company;

public class Clerk extends AbstractEmployees {
    private String name;
    private double salary = 0.0;

    public Clerk(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    protected void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public double getMonthSalary() {
        return salary;
    }
}