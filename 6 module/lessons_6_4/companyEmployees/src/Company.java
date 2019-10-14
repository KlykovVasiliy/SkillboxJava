import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Company {
//    public int incomeCompany = 0;
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

    public int getIncomeCompany() {                                                                 //после промежуточной проверки
        int income = 0;                                                                             //будет заменен на private
        for (Employee employee : listEmployeesCompany) {
            income += employee.getEmployeeRevenue();
        }
        return  income;
    }

    private int getCountSalesMan() {
        return countSalesMan;
    }

    private List<AbstractEmployees> listEmployeesCompany = new ArrayList<>(270);
    private Comparator<AbstractEmployees> comparator = new ComparatorSalary().thenComparing
            (new ComparatorName());

//    private void increaseIncomeCompany(AbstractEmployees employee, double income) {
//        if (employee instanceof SalesMan) {                                                         //заменить instanceof
//            incomeCompany += income;
//        } else {
//            System.err.println("Доход в фирму могут приносить только менеджеры по прадажам.");
//        }
//    }

    public void recruitEmployees(int countManager, int salesManager, int clearkMan) {
        for (int i = 0; i < countManager; i++) {
            TopManager topManager = new TopManager("topManager m" + i);
            listEmployeesCompany.add(topManager);
            for (int a = 1; a <= salesManager / countManager; a++) {
                SalesMan salesMan = new SalesMan("salesMan s" + i + a);
                listEmployeesCompany.add(salesMan);
//                increaseIncomeCompany(salesMan, salesMan.getSales());
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
            if (ob instanceof TopManager) {                                                         //заменить instanceof
                deductionIncome = 40000.0;
                int teenMillions = 10000000;
                if (getIncomeCompany() > teenMillions) {
                    double persentageOfIncomeCompany = 0.05;
                    deductionIncome = getIncomeCompany() * persentageOfIncomeCompany /
                            countTopManager + 40000.0;
                }
                ob.setSalary(deductionIncome);
            } else if (ob instanceof SalesMan) {                                                    //заменить instanceof
                deductionIncome = ob.getMonthSalary() + 30000.0;
                ob.setSalary(deductionIncome);
            } else if (ob instanceof Clerk) {                                                       //заменить instanceof
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

    public List<AbstractEmployees> getTopSalaryStaff(int count) {
        int topSalaryStart = listEmployeesCompany.size();
        return listEmployeesCompany.subList(topSalaryStart - count, topSalaryStart - 1);
    }

    public List<AbstractEmployees> getLowestSalaryStaff(int count) {
        return listEmployeesCompany.subList(0, count - 1);
    }
}