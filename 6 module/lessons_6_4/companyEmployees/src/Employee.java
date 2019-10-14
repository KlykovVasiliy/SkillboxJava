public interface Employee {
    public double getMonthSalary();
    public default double getEmployeeRevenue() {
        return 0;
    }
}
