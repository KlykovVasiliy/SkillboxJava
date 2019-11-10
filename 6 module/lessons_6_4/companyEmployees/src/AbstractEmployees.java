public abstract class AbstractEmployees implements Employee {
    private String name;
    private int salary;

    protected AbstractEmployees(String name) {
        this.name = name;
    }

    protected String getName() {
        return name;
    }

    protected static int generateASalary(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setSalary(int salary) {
        this.salary = salary;
    }
}