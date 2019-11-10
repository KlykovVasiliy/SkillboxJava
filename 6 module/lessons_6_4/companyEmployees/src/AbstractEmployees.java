public abstract class AbstractEmployees {
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

    protected static int generateASalary(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public double getMonthSalary() {
        return salary;
    }

    protected void setSalary(int salary) {
        this.salary = salary;
    }


    public double getEmployeeRevenue(){
        return 0;
    }
}