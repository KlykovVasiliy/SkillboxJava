public class TopManager extends AbstractEmployees {
    private double salary = 0.0;

    public TopManager(String name) {
        super(name);
    }

    @Override
    public void setSalary(double salary) {
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