import Company.*;


public class Main {
    public static void main(String[] args) {
        //наём 270 сотрудников
        Company company = new Company();
        company.recruitEmployees(10, 170, 90);

        company.printListEmployeesCompany();


    }
}
