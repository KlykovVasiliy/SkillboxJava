import java.util.List;

public class Main {
    public static void main(String[] args) {
        Company company = new Company();
        List<AbstractEmployees> list;

        //наём 270 сотрудников
        company.recruitEmployees(90);
        System.out.println("Доход компании в самом начале " + company.getIncomeCompany());
        System.out.println("Список сотрудников организации с зарплатами.");
        company.printListEmployeesCompany();
        System.out.printf("Вывод списка сотрудников организации с зарплатами завершен.%n" +
                "===========================================================================%n%n");

        for (int i = 0; i < 2; i++) {
            list = company.getLowestSalaryStaff(10);
            System.out.println("Вывод топ низких зарплат.");
            for (AbstractEmployees em : list) {
                System.out.println(em.getMonthSalary());
            }
            System.out.println();

            list = company.getTopSalaryStaff(10);
            System.out.println("Вывод топ высоких зарплат.");
            for (AbstractEmployees em : list) {
                System.out.println(em.getMonthSalary());
            }
            System.out.println();

            if (i == 0) {
                company.fireAnEmployees(20);
            }
        }

        System.out.println("Вывод списка сотрудников после увольнения");
        System.out.println("Доход компании составляет " + company.getIncomeCompany());
        company.printListEmployeesCompany();

        System.out.printf("%n%nТоп менеджеры одумались, поняли что они накосячили по крупному" +
                "и решили нанять вновь продажников.%n");
        company.recruitSalesMan(15);
        System.out.println("Доход компании после принятия на работу продажников составляет "
                + company.getIncomeCompany());
        company.printListEmployeesCompany();

    }
}