public abstract class AbstractEmployees implements Employee {
    private String name;
    private double salary = 0.0;

    protected AbstractEmployees(String name) {
        this.name = name;
    }

    protected String getName() {
        return name;
    }

    protected void setSalary(double salary) {
        this.salary = salary;
    }
}