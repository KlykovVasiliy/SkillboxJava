import java.util.List;

public class Main {
    public static void main(String[] args) {
        Company company = Company.createCompany();
        Company company2 = Company.createCompany();
        List<AbstractEmployees> list;

        //наём 270 сотрудников
        company.recruitEmployees(company, 90);
        company2.recruitEmployees(company2, 20);
        System.out.println("Доход компании в самом начале " + company.getIncomeCompany());
        System.out.println("Список сотрудников организации с зарплатами.");
        company.printListEmployeesCompany();
        System.out.printf("Вывод списка сотрудников организации с зарплатами завершен.%n" +
                "===========================================================================%n%n");

        for (int i = 0; i < 2; i++) {
            list = company.getLowestSalaryStaff(10);
            System.out.println("Вывод топ низких зарплат.");
            int lowestCount = 0;
            for (AbstractEmployees em : list) {
                lowestCount++;
                System.out.println(lowestCount + " " + em.getMonthSalary());
            }
            System.out.println();

            list = company.getTopSalaryStaff(10);
            System.out.println("Вывод топ высоких зарплат.");
            int topCount = 0;
            for (AbstractEmployees em : list) {
                topCount++;
                System.out.println(topCount + " " + em.getMonthSalary());
            }
            System.out.println();

            if (i == 0) {
                company.fireAnEmployees(20);
                System.out.println("Вывод списка сотрудников после увольнения");
                System.out.println("Доход компании составляет " + company.getIncomeCompany());
                company.printListEmployeesCompany();
                System.out.println();
            }
        }

        System.out.printf("%n%nТоп менеджеры одумались, поняли что они накосячили по крупному" +
                "и решили нанять вновь продажников.%n");
        company.recruitSalesMan(company, 15);
        System.out.println("Доход компании после принятия на работу продажников составляет "
                + company.getIncomeCompany());
        company.printListEmployeesCompany();

        System.out.printf("%n%n");
        System.out.println("Доход компании2 в самом начале " + company2.getIncomeCompany());
        System.out.println("Список сотрудников организации с зарплатами.");
        company2.printListEmployeesCompany();
    }
}