import java.util.ArrayList;
import java.util.Comparator;

public class Company {
    private int incomeCompany;
    private int countTopManager = 0;
    private final static double PERSENTAGE_OF_INCOME_COMPANY = 0.05;

    private ArrayList<AbstractEmployees> listEmployeesCompany = new ArrayList<>(270);
    private Comparator<AbstractEmployees> comparator = new ComparatorSalary().thenComparing
            (new ComparatorName());
    private ArrayList<Employee> list = new ArrayList<>();

    public int getCountTopManager() {       //позже можно убрать
        return countTopManager;
    }

    public int getIncomeCompany() {
        return incomeCompany;
    }

    public void increaseIncomeCompany(AbstractEmployees employee, double income) {
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
        for (Object ob : listEmployeesCompany) {
            if (ob instanceof TopManager) {
                int teenMillions = 10000000;
                if (incomeCompany > teenMillions) {
                    ((TopManager) ob).setSalary(incomeCompany * PERSENTAGE_OF_INCOME_COMPANY /
                            countTopManager + 40000.0);
                } else {
                    ((TopManager) ob).setSalary(40000.0);
                }
            } else if (ob instanceof SalesMan) {
                ((SalesMan) ob).setSalary(((SalesMan) ob).getMonthSalary() + 30000.0);

            } else if (ob instanceof Clerk) {
                ((Clerk) ob).setSalary(25000.0);
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
        for (Object ob : listEmployeesCompany) {
            if (ob instanceof TopManager) {
                System.out.println(((TopManager) ob).getName() + " " + ((TopManager) ob).getMonthSalary());
            } else if (ob instanceof SalesMan) {
                System.out.println(((SalesMan) ob).getName() + " " + ((SalesMan) ob).getMonthSalary());
            } else if (ob instanceof Clerk) {
                System.out.println(((Clerk) ob).getName() + " " + ((Clerk) ob).getMonthSalary());
            }
        }
    }

    public ArrayList<Employee> getTopSalaryStaff(int count) {
        list.clear();
        int topSalaryStart = listEmployeesCompany.size() - 1;
        for (int i = topSalaryStart; i > topSalaryStart - count; i--) {
            Object ob = listEmployeesCompany.get(i);
            if (ob instanceof Employee) {
                Employee employee = (Employee) ob;
                list.add(employee);
            }
        }
        return list;
    }

    public ArrayList<Employee> getLowestSalaryStaff(int count) {
        list.clear();
        for (int i = 0; i < count; i++) {
            Object ob = listEmployeesCompany.get(i);
            if (ob instanceof Employee) {
                Employee employee = (Employee) ob;
                list.add(employee);
            }
        }
        return list;
    }
}