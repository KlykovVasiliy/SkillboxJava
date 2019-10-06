package Company;

public class TopManager extends AbstractEmployees {
    private double salary = 0.0;

    public TopManager(String name) {
        super(name);
        setCountTopManager();
    }

    protected void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public double getMonthSalary() {
        return salary;
    }
}