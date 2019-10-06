package Company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Company {
    private int incomeCompany = 0;
    private int countTopManager = 0;
    protected final static double PERSENTAGE_OF_INCOME_COMPANY = 0.05;

    private ArrayList<Company> listEmployeesCompany = new ArrayList<>(270);
    private SalaryComparator salaryComparator = new SalaryComparator();



    protected void setCountTopManager() {
        this.countTopManager += 1;
    }

    private int getCountTopManager() {                                    //получение количества менеджеров
        return countTopManager;
    }

    public int getIncomeCompany() {
        return incomeCompany;
    }

    protected void increaseIncomeCompany(int income) {
        incomeCompany += income;
    }

    public void recruitEmployees(int countManager, int salesManager, int clearkMan) {
        for (int i = 0; i < countManager; i++) {
            TopManager topManager = new TopManager("topManager m" + i);
            listEmployeesCompany.add(topManager);
            for (int a = 1; a <= salesManager/countManager; a++) {
                SalesMan salesMan = new SalesMan("salesMan s" + i + a);
                listEmployeesCompany.add(salesMan);
            }
            for (int b = 1; b <= clearkMan/countManager; b++) {
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
        Arrays.sort(salaryComparator);
//        Collections.sort(listEmployeesCompany, salaryComparator);
    }


    private int randomEmployeeSelection() {
        return (int) (Math.random() * listEmployeesCompany.size());
    }

    public void fireEmployees() {
        for (int i = 0; i < 10; i++) {
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
        return null;
    }

    public ArrayList<Employee> getLowestSalaryStaff(int count) {
        return null;
    }
}


