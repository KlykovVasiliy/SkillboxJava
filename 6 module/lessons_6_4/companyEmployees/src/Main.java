import java.util.List;

public class Main {
    public static void main(String[] args) {
        Company company = new Company();
        List<AbstractEmployees> list;

        //наём 270 сотрудников
        company.recruitEmployees(10, 170, 90);

        System.out.println("Список сотрудников организации.");
        company.printListEmployeesCompany();
        System.out.printf("Вывод списка сотрудников организации с зарплатами завершен.%n" +
                "===========================================================================%n%n");

        for (int i = 0; i < 2; i++) {
            list = company.getLowestSalaryStaff(10);
            System.out.println("Вывод топ низких зарплат.");
            for (Employee em : list) {
                System.out.println(em.getMonthSalary());
            }
            System.out.println();

            list = company.getTopSalaryStaff(10);
            System.out.println("Вывод топ высоких зарплат.");
            for (Employee em : list) {
                System.out.println(em.getMonthSalary());
            }
            System.out.println();

            if (i == 0) {
                company.fireEmployees();
            }
        }
    }
}