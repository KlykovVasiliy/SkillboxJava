import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Company {
    private static final double PERSENT_TOP_MANAGER = 0.05;
    private static final double PERSENT_SALES = 0.65;
    private static final double PERSENT_CLEARK = 0.3;

    private List<AbstractEmployees> listEmployeesCompany = new ArrayList<>(270);
    private Comparator<AbstractEmployees> comparator = new ComparatorSalary().thenComparing
            (new ComparatorName());

    public void recruitEmployees(int countEmployees) {                                              //наём сотрудников в компанию
        for (int i = 0; i < countEmployees * PERSENT_SALES; i++) {
            listEmployeesCompany.add(SalesMan.createSalesMan("SalesMan" + i));
        }
        for (int i = 0; i < countEmployees * PERSENT_CLEARK; i++) {
            listEmployeesCompany.add(Clerk.createCleark("Cleark" + i));
        }
        for (int i = 0; i < countEmployees * PERSENT_TOP_MANAGER; i++) {
            listEmployeesCompany.add(TopManager.createTopManager("TopManager" + i));
        }
        if (isIncomeOverTenMillions()) {
            appointPremiaTopManager();
        }
        listEmployeesCompany.sort(comparator);
    }

    private int getIncomeCompany() {                                                                //получение дохода компании
        int income = 0;
        for (Employee employee : listEmployeesCompany) {
            income += employee.getEmployeeRevenue();
        }
        return income;
    }

    private boolean isIncomeOverTenMillions() {                                                       //проверка что доход компании
        int tenMillions = 10000000;                                                                 //более 10млн
        return getIncomeCompany() > tenMillions;
    }

    private int getCountTopManager() {                                                              //получение числа топ менеджеров
        return (int) listEmployeesCompany.stream().filter(e -> e instanceof TopManager).count();
    }

    private void appointPremiaTopManager() {                                                        //назначение премиии топ менеджеру
        double persentageOfIncomeCompany = 0.05;
        double salaryTopManager =
                getIncomeCompany() * persentageOfIncomeCompany / getCountTopManager();
        for (AbstractEmployees ob : listEmployeesCompany) {
            if (ob instanceof TopManager) {
                ((TopManager) ob).giveAPremia(salaryTopManager);
            }
        }
    }

    private int generateRandomEmployeeSelection() {                                                 //генерация случайной выборки
        return (int) (Math.random() * listEmployeesCompany.size());
    }

    public void fireAnEmployees() {                                                                   //увольнение сотрудников
        int removeCount = 0;
        for (int i = 0; i < listEmployeesCompany.size(); i++) {
            int removeEmployee = generateRandomEmployeeSelection();
            if (listEmployeesCompany.get(removeEmployee) instanceof SalesMan) {                     //заменить instanceof
                if (!isIncomeOverTenMillions()) {
                    System.out.println("Менеджер по продажам не может быть уволен, иначе дохода " +
                            "компании будет недостаточно для премирования топ менеджеров.");
                } else {
                    listEmployeesCompany.remove(removeEmployee);
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