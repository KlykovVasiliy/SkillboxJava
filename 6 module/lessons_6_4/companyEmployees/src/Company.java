import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

public class Company {
    private static final double PERSENT_TOP_MANAGER = 0.05;
    private static final double PERSENT_SALES = 0.65;
    private static final double PERSENT_CLEARK = 0.3;

    private List<AbstractEmployees> listEmployeesCompany = new ArrayList<>(270);
    private Comparator<AbstractEmployees> comparator = new ComparatorSalary().thenComparing
            (new ComparatorName());

    public void recruitEmployees(int countEmployees) {
        recruitTopManager((int)(countEmployees * PERSENT_TOP_MANAGER));
        recruitSalesMan((int) (countEmployees * PERSENT_SALES));
        recruitCleark((int) (countEmployees * PERSENT_CLEARK));
    }

    public void recruitTopManager(int countEmployees) {
        addEmployees(countEmployees, ()-> TopManager.createTopManager());
        sortEmployees();
    }

    public void recruitSalesMan(int countEmployees) {
        addEmployees(countEmployees, ()-> SalesMan.createSalesMan());
        appointDeprivePremiaTopManager();
        sortEmployees();
    }

    public void recruitCleark(int countEmployees) {
        addEmployees(countEmployees, ()-> Clerk.createCleark());
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

    private boolean isIncomeOverTenMillions() {                                                     //проверка что доход компании
        int tenMillions = 10000000;                                                                 //более 10млн
        return getIncomeCompany() > tenMillions;
    }

    private int getCountTopManager() {                                                              //получение числа топ менеджеров
        return (int) listEmployeesCompany.stream().filter(e -> e instanceof TopManager).count();
    }

    private void appointDeprivePremiaTopManager() {                                                        //назначение премиии топ менеджеру
        double persentageOfIncomeCompany = 0.05;
        if (isIncomeOverTenMillions()) {
            double salaryTopManager =
                    getIncomeCompany() * persentageOfIncomeCompany / getCountTopManager();
            for (AbstractEmployees ob : listEmployeesCompany) {
                if (ob instanceof TopManager) {
                    ((TopManager) ob).giveAPremia(salaryTopManager);
                }
            }
        } else {
            deprivePremia();
        }
    }

    private void deprivePremia() {
        for (AbstractEmployees ob : listEmployeesCompany) {
            if (ob instanceof TopManager) {
                ((TopManager) ob).depriveBonus();
            }
        }
    }

    private int generateRandomEmployeeSelection() {                                                 //генерация случайной выборки
        return (int) (Math.random() * listEmployeesCompany.size());
    }

    public void fireAnEmployees(int count) {                                                                   //увольнение сотрудников
        for (int i = 0; i < count; i++) {
            listEmployeesCompany.remove(generateRandomEmployeeSelection());
        }
        appointDeprivePremiaTopManager();
        sortEmployees();
    }

    public void fireAnSalesMan() {
        int removeEmployee = generateRandomEmployeeSelection();
        if (listEmployeesCompany.get(removeEmployee) instanceof SalesMan) {
            listEmployeesCompany.remove(removeEmployee);
        }
        appointDeprivePremiaTopManager();
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
        return listEmployeesCompany.subList(topSalaryStart - count, topSalaryStart - 1);
    }

    public List<AbstractEmployees> getLowestSalaryStaff(int count) {
        return listEmployeesCompany.subList(0, count - 1);
    }
}