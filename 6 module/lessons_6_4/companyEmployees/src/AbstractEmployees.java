public abstract class AbstractEmployees {
    private String name;
    private int salary;
    private Company company;

    AbstractEmployees(String name, int salary, Company company) {
        this.name = name;
        this.salary = salary;
        this.company = company;
    }

    protected Company getCompany() {
        return company;
    }

    String getName() {
        return name;
    }


    protected static int generateASalary(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public int getMonthSalary() {
        return salary;
    }

    protected int getSalary() {
        return salary;
    }

    protected void setSalary(int salary) {
        this.salary = salary;
    }

    public double getEmployeeRevenue() {
        return 0;
    }
}