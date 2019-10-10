public class TopManager extends AbstractEmployees {
    private double salary = 0.0;

    public TopManager(String name) {
        super(name);
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public double getMonthSalary() {
        return salary;
    }
}