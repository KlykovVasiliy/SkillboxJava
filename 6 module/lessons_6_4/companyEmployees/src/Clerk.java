public class Clerk extends AbstractEmployees {
    private double salary = 0.0;

    public Clerk(String name) {
        super(name);
    }

    @Override
    protected void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public double getMonthSalary() {
        return salary;
    }
}