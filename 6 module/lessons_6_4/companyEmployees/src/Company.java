import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

public class Company {
    private static final double PERSENT_TOP_MANAGER = 0.05;
    private static final double PERSENT_SALES = 0.65;
    private static final double PERSENT_CLEARK = 0.3;
    private static final int SALES_PLAN = 10000000;

    private Company() {
    }

    public static Company createCompany() {
        return new Company();
    }

    private List<AbstractEmployees> listEmployeesCompany = new ArrayList<>(270);
    private Comparator<AbstractEmployees> comparator = new ComparatorSalary().thenComparing
            (new ComparatorName());

    public void recruitEmployees(Company company, int countEmployees) {
        recruitTopManager(company, (int) (countEmployees * PERSENT_TOP_MANAGER));
        recruitSalesMan(company, (int) (countEmployees * PERSENT_SALES));
        recruitCleark(company, (int) (countEmployees * PERSENT_CLEARK));
        sortEmployees();
    }

    public void recruitTopManager(Company company, int countEmployees) {
        addEmployees(countEmployees, () -> TopManager.createTopManager(company));
        sortEmployees();
    }

    public void recruitSalesMan(Company company, int countEmployees) {
        addEmployees(countEmployees, () -> SalesMan.createSalesMan(company));
        sortEmployees();
    }

    public void recruitCleark(Company company, int countEmployees) {
        addEmployees(countEmployees, () -> Clerk.createCleark(company));
        sortEmployees();
    }


    private void addEmployees(int countEmployees, Supplier<AbstractEmployees> generator) {
        for (int i = 0; i < countEmployees; i++) {
            listEmployeesCompany.add(generator.get());
        }
    }

    public void sortEmployees() {
        listEmployeesCompany.sort(comparator);
    }

    public int getIncomeCompany() {                                                                //получение дохода компании
        int income = 0;
        for (AbstractEmployees employee : listEmployeesCompany) {
            income += employee.getEmployeeRevenue();
        }
        return income;
    }

    protected boolean isIncomeOverSalesPlan() {
        return getIncomeCompany() > SALES_PLAN;
    }

    public int getPremiaTopManager() {
        double persentageOfIncomeCompany = 0.05;
        return (int) (getIncomeCompany() * persentageOfIncomeCompany / getCountTopManager());
    }

    private int getCountTopManager() {                                                              //получение числа топ менеджеров
        return (int) listEmployeesCompany.stream().filter(e -> e instanceof TopManager).count();
    }

    private int generateRandomEmployeeSelection() {                                                 //генерация случайной выборки
        return (int) (Math.random() * listEmployeesCompany.size());
    }

    public void fireAnEmployees(int count) {                                                                   //увольнение сотрудников
        for (int i = 0; i < count; i++) {
            listEmployeesCompany.remove(generateRandomEmployeeSelection());
        }
    }

    public void fireAnSalesMan() {
        int removeEmployee = generateRandomEmployeeSelection();
        if (listEmployeesCompany.get(removeEmployee) instanceof SalesMan) {
            listEmployeesCompany.remove(removeEmployee);
        }
    }

    public void fireAnCleark() {
        int removeEmployee = generateRandomEmployeeSelection();
        if (listEmployeesCompany.get(removeEmployee) instanceof Clerk) {
            listEmployeesCompany.remove(removeEmployee);
        }
    }

    public void fireAnTopManager() {
        int removeEmployee = generateRandomEmployeeSelection();
        if (listEmployeesCompany.get(removeEmployee) instanceof TopManager) {
            listEmployeesCompany.remove(removeEmployee);
        }
    }

    public void printListEmployeesCompany() {
        int count = 1;
        for (AbstractEmployees ob : listEmployeesCompany) {
            System.out.println(count + " " + ob.getName() + " " + ob.getMonthSalary());
            count++;
        }
    }

    public List<AbstractEmployees> getTopSalaryStaff(int count) {
        int topSalaryStart = listEmployeesCompany.size();
        return listEmployeesCompany.subList(topSalaryStart - count, topSalaryStart);
    }

    public List<AbstractEmployees> getLowestSalaryStaff(int count) {
        return listEmployeesCompany.subList(0, count);
    }
}