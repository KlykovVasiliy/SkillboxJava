import java.util.ArrayList;
import java.util.Comparator;

public class Company {
    private int incomeCompany;
    private int countTopManager = 0;

    private ArrayList<AbstractEmployees> listEmployeesCompany = new ArrayList<>(270);
    private Comparator<AbstractEmployees> comparator = new ComparatorSalary().thenComparing
            (new ComparatorName());

    private void increaseIncomeCompany(AbstractEmployees employee, double income) {
        if (employee instanceof SalesMan) {
            incomeCompany += income;
        } else {
            System.err.println("Доход в фирму могут приносить только менеджеры по прадажам.");
        }
    }

    public void recruitEmployees(int countManager, int salesManager, int clearkMan) {
        for (int i = 0; i < countManager; i++) {
            TopManager topManager = new TopManager("topManager m" + i);
            listEmployeesCompany.add(topManager);
            countTopManager++;
            for (int a = 1; a <= salesManager / countManager; a++) {
                SalesMan salesMan = new SalesMan("salesMan s" + i + a);
                listEmployeesCompany.add(salesMan);
                increaseIncomeCompany(salesMan, salesMan.getSales());
            }
            for (int b = 1; b <= clearkMan / countManager; b++) {
                Clerk cleark = new Clerk("cleark c" + i + b);
                listEmployeesCompany.add(cleark);
            }
        }
        setSalaryEmployees();
    }

    private void setSalaryEmployees() {
        for (AbstractEmployees ob : listEmployeesCompany) {
            if (ob instanceof TopManager) {
                int teenMillions = 10000000;
                if (incomeCompany > teenMillions) {
                    double persentageOfIncomeCompany = 0.05;
                    ob.setSalary(incomeCompany * persentageOfIncomeCompany /
                            countTopManager + 40000.0);
                } else {
                    ob.setSalary(40000.0);
                }
            } else if (ob instanceof SalesMan) {
                ob.setSalary(ob.getMonthSalary() + 30000.0);

            } else if (ob instanceof Clerk) {
                ob.setSalary(25000.0);
            }
        }
        listEmployeesCompany.sort(comparator);
    }

    private int randomEmployeeSelection() {
        return (int) (Math.random() * listEmployeesCompany.size());
    }

    public void fireEmployees() {                                   //увольнение сотрудников
        for (int i = 0; i < listEmployeesCompany.size() / 10; i++) {
            listEmployeesCompany.remove(randomEmployeeSelection());
        }
    }

    public void printListEmployeesCompany() {
        for (AbstractEmployees ob : listEmployeesCompany) {
            System.out.println(ob.getName() + " " + ob.getMonthSalary());
        }
    }

    public ArrayList<Employee> getTopSalaryStaff(int count) {
        ArrayList<Employee> list = new ArrayList<>();
        int topSalaryStart = listEmployeesCompany.size();
        for (AbstractEmployees ob : listEmployeesCompany.subList(topSalaryStart - count,
                topSalaryStart - 1)) {
            list.add((Employee) ob);
        }
        return list;
    }

    public ArrayList<Employee> getLowestSalaryStaff(int count) {
        ArrayList<Employee> list = new ArrayList<>();
        for (AbstractEmployees ob : listEmployeesCompany.subList(0, count - 1)) {
                list.add((Employee) ob);
        }
        return list;
    }
}