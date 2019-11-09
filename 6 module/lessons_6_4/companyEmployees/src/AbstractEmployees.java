public abstract class AbstractEmployees implements Employee {
    private String name;
    private int salary;

    protected AbstractEmployees(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setSalary(int salary) {
        this.salary = salary;
    }
}