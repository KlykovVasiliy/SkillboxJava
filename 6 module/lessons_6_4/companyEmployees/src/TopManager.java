public class TopManager extends AbstractEmployees {
    private static final int MIN_SALARY = 30000;
    private static final int MAX_SALARY = 40000;
    private final int salaryWithoutPremia;

    private TopManager(String name, int salary, Company company) {
        super(name, salary, company);
        salaryWithoutPremia = salary;
        setSalary(salary);
    }

    public TopManager rename(String newName) {                                                      //изменить имя
        return new TopManager(newName, getMonthSalary(), getCompany());
    }

    public static TopManager createTopManager(Company company) {
        return new TopManager("TopManager", generateASalary(MIN_SALARY, MAX_SALARY), company);
    }

    private void giveAPremia() {                                                                    //добавить премию
        setSalary(salaryWithoutPremia + getCompany().getPremiaTopManager());
    }

    private void depriveBonus() {                                                                  //лишить премии
        setSalary(salaryWithoutPremia);
    }

    private boolean isPremiaNotBeen() {                                                             //проверка на отсутствие премии
        return salaryWithoutPremia == getSalary();
    }

    @Override
    public int getMonthSalary() {
        if (getCompany().isIncomeOverSalesPlan() && isPremiaNotBeen()) {
            giveAPremia();
            return getSalary();
        } else if (!getCompany().isIncomeOverSalesPlan() && !isPremiaNotBeen()) {
            depriveBonus();
            return getSalary();
        } else {
            return getSalary();
        }
    }
}