package Company;

public class TopManager extends AbstractEmployees {
    private String name;
    private double salary = 0.0;

    public TopManager(String name) {
        this.name = name;
        setCountTopManager();
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