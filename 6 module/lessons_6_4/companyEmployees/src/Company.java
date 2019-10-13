import java.util.ArrayList;
import java.util.Comparator;

public class Company {
    private int incomeCompany = 0;
    private int countTopManager = 0;
    private int countSalesMan = 0;

    private void setCountTopManager(int count) {
        this.countTopManager = count;
    }

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
        setCountTopManager(countManager);
        setCountSalesMan(salesManager);
        setSalaryEmployees();
    }

    private void setSalaryEmployees() {
        for (AbstractEmployees ob : listEmployeesCompany) {
            double deductionIncome;
            if (ob instanceof TopManager) {
                deductionIncome = 40000.0;
                int teenMillions = 10000000;
                if (incomeCompany > teenMillions) {
                    double persentageOfIncomeCompany = 0.05;
                    deductionIncome = incomeCompany * persentageOfIncomeCompany /
                            countTopManager + 40000.0;
                }
                ob.setSalary(deductionIncome);
            } else if (ob instanceof SalesMan) {
                deductionIncome = ob.getMonthSalary() + 30000.0;
                ob.setSalary(deductionIncome);
            } else if (ob instanceof Clerk) {
                deductionIncome = 25000.0;
                ob.setSalary(deductionIncome);
            }
        }
        listEmployeesCompany.sort(comparator);
    }

    private int randomEmployeeSelection() {
        return (int) (Math.random() * listEmployeesCompany.size());
    }

    public void fireEmployees() {                                                                   //увольнение сотрудников
        int removeCount = 0;
        for (int i = 0; i < listEmployeesCompany.size(); i++) {
            int removeEmployee = randomEmployeeSelection();
            if (listEmployeesCompany.get(removeEmployee) instanceof SalesMan) {
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
                System.out.printf("Увольнение 10 процентов сотрудников компании. " +
                        "Уволено %d сотрудников.%n", removeCount);
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
        System.out.printf("Доход будет составлять %d%n", (int) income);
        return teenMillions < income;
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