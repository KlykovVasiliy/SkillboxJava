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
        addEmployees((int) (countEmployees * PERSENT_TOP_MANAGER),
                ()-> TopManager.createTopManager());
        addEmployees((int) (countEmployees * PERSENT_SALES),
                ()-> SalesMan.createSalesMan());
        addEmployees((int) (countEmployees * PERSENT_CLEARK),
                ()-> Clerk.createCleark());
        appointPremiaTopManager();
        listEmployeesCompany.sort(comparator);
    }

    private void addEmployees(int countEmployees, Supplier<AbstractEmployees> generator) {
        for (int i = 0; i < countEmployees; i++) {
            listEmployeesCompany.add(generator.get());
        }
    }

    private int getIncomeCompany() {                                                                //получение дохода компании
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

    private void appointPremiaTopManager() {                                                        //назначение премиии топ менеджеру
        double persentageOfIncomeCompany = 0.05;
        if (isIncomeOverTenMillions()) {
            double salaryTopManager =
                    getIncomeCompany() * persentageOfIncomeCompany / getCountTopManager();
            for (AbstractEmployees ob : listEmployeesCompany) {
                if (ob instanceof TopManager) {
                    ((TopManager) ob).giveAPremia(salaryTopManager);
                }
            }
        }
    }

    private int generateRandomEmployeeSelection() {                                                 //генерация случайной выборки
        return (int) (Math.random() * listEmployeesCompany.size());
    }

    public void fireAnEmployees() {                                                                   //увольнение сотрудников
        int removeCount = 0;
        while (removeCount <= listEmployeesCompany.size() / 10) {
            listEmployeesCompany.remove(generateRandomEmployeeSelection());
            removeCount++;
        }
    }

    public void printListEmployeesCompany() {
        for (AbstractEmployees ob : listEmployeesCompany) {
            System.out.println(ob.getName() + " " + ob.getMonthSalary());
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