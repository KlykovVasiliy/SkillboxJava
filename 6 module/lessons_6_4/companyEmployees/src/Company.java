import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Company {
    private int countSalesMan = 0;

    private List<AbstractEmployees> listEmployeesCompany = new ArrayList<>(270);
    private Comparator<AbstractEmployees> comparator = new ComparatorSalary().thenComparing
            (new ComparatorName());

    private void setCountSalesMan(int count) {
        if (count > 0) {
            this.countSalesMan = count;
        }
        if (count < 0) {
            countSalesMan -= count;
        }
    }

    private int getCountSalesMan() {
        return countSalesMan;
    }

    private int getIncomeCompany() {
        int income = 0;
        for (Employee employee : listEmployeesCompany) {
            income += employee.getEmployeeRevenue();
        }
        return income;
    }

    public void recruitEmployees(int countManager, int salesManager, int clearkMan) {
        for (int i = 0; i < countManager; i++) {
            TopManager topManager = new TopManager("topManager m" + i);
            double salaryManagerWithoutPremia = 40000.0;
            topManager.setSalary(salaryManagerWithoutPremia);
            listEmployeesCompany.add(topManager);
            for (int a = 1; a <= salesManager / countManager; a++) {
                SalesMan salesMan = new SalesMan("salesMan s" + i + a);
                double salarySalesMan = 30000.0;
                salesMan.setSalary(salesMan.getMonthSalary() + salarySalesMan);
                listEmployeesCompany.add(salesMan);
            }
            for (int b = 1; b <= clearkMan / countManager; b++) {
                Clerk cleark = new Clerk("cleark c" + i + b);
                double salaryCleark = 25000.0;
                cleark.setSalary(salaryCleark);
                listEmployeesCompany.add(cleark);
            }
        }
        int teenMillions = 10000000;
        if (getIncomeCompany() > teenMillions) {
            salaryTopManager(countManager);
        }
        setCountSalesMan(salesManager);
        listEmployeesCompany.sort(comparator);
    }

    private void salaryTopManager(int countManager) {
        double persentageOfIncomeCompany = 0.05;
        double salaryTopManager = getIncomeCompany() * persentageOfIncomeCompany / countManager;
        for (AbstractEmployees ob : listEmployeesCompany) {
            if (ob instanceof TopManager) {
                ob.setSalary(ob.getMonthSalary() + salaryTopManager);
            }
        }
    }

    private int randomEmployeeSelection() {
        return (int) (Math.random() * listEmployeesCompany.size());
    }

    public void fireEmployees() {                                                                   //увольнение сотрудников
        int removeCount = 0;
        for (int i = 0; i < listEmployeesCompany.size(); i++) {
            int removeEmployee = randomEmployeeSelection();
            if (listEmployeesCompany.get(removeEmployee) instanceof SalesMan) {                     //заменить instanceof
                if (!salesRevenueMoreThreshold()) {
                    System.out.println("Менеджер по продажам не может быть уволен, иначе дохода " +
                            "компании будет недостаточно для премирования топ менеджеров.");
                } else {
                    listEmployeesCompany.remove(removeEmployee);
                    setCountSalesMan(-1);
                    removeCount++;
                }
            } else {
                listEmployeesCompany.remove(removeEmployee);
                removeCount++;
            }
            if (removeCount >= listEmployeesCompany.size() / 10) {
                break;
            }
        }
    }

    private boolean salesRevenueMoreThreshold() {
        double income = 0.0;
        for (int i = 0; i < getCountSalesMan() - 1; i++) {
            SalesMan salesMan = new SalesMan("salesMan " + i + " после увольнения");
            income += salesMan.getSales();
        }
        double teenMillions = 10000000.0;
        return teenMillions < income;
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