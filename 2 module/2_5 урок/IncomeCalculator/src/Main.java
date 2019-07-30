import java.util.Scanner;

public class Main
{
    private static int minIncome = 200000;                          //минимальный доход
    private static int maxIncome = 900000;                          //максимальный доход

    private static int officeRentCharge = 140000;                   //арендная плата за офис
    private static int telephonyCharge = 12000;                     //оплата за телефон
    private static int internetAccessCharge = 7200;                 //оплата за доступ в интернет

    private static int assistantSalary = 45000;                     //зарплата помощника
    private static int financeManagerSalary = 90000;                //зарплата финансового менеджера

    private static double mainTaxPercent = 0.24;                    //процент основного налога
    private static double managerPercent = 0.15;                    //процент менеджера

    private static double minInvestmentsAmount = 100000;            //минимальная сумма инвестиций

    public static void main(String[] args)
    {
        int minIncomeCompany = calculateFixedCharges () + (int)(calculateFixedCharges() * mainTaxPercent) +
                (int)(calculateFixedCharges() * managerPercent) + (int)minInvestmentsAmount;
        System.out.println("Минимальный доход компании для осуществления инвестиций состовляет " + minIncomeCompany + " рублей.");
        while(true)                                                     //бесконечный цикл while пока условие истинно
        {
            System.out.println("Введите сумму доходов компании за месяц " +
                "(от 200 до 900 тысяч рублей): ");
            int income = (new Scanner(System.in)).nextInt();            //ввод сумма дохода компании

            if(!checkIncomeRange(income)) {                             //проверка, соотвествует доход компании заданному диапозону
                continue;                                               //если не соответствует, то переход к следующей итерации цикла
            }

            double managerSalary = income * managerPercent;             //расчет зарплаты менеджера
            double pureIncome = income - managerSalary -                //расчет чистого дохода. Из дохода вычитается зарплата менеджера и сумма фиксированных расходов
                calculateFixedCharges();
            double taxAmount = mainTaxPercent * pureIncome;             //расчет суммы налога. Процент основного налога умножается на чистый доход
            double pureIncomeAfterTax = pureIncome - taxAmount;         //определение чистого дохода после уплаты налога

            boolean canMakeInvestments = pureIncomeAfterTax >=          //определение может ли компания инвестировать или нет
                minInvestmentsAmount;

            if(!canMakeInvestments) {
                System.out.println("Увеличьте доход, компание для осуществления инвестирования необходимо больше зарабатывать!");
                continue;
            }

            System.out.println("Зарплата менеджера: " + managerSalary);     //вывод на экран зарплаты менеджера
            System.out.println("Общая сумма налогов: " +                    //если сумма налога больше нуля то
                (taxAmount > 0 ? taxAmount : 0));                           //вывод на экран суммы налога, а иначе 0
            System.out.println("Компания может инвестировать: " +           //Вывод на экран может ли компания инвестировать
                (canMakeInvestments ? "да" : "нет"));
            if(pureIncome < 0) {
                System.out.println("Бюджет в минусе! Нужно срочно зарабатывать!");
            }
        }
    }

    private static boolean checkIncomeRange(int income)                     //метод определяющий уровень дохода
    {                                                                       //доход меньше нижней и выше верхней границы?
        if(income < minIncome)
        {
            System.out.println("Доход меньше нижней границы");
            return false;
        }
        if(income > maxIncome)
        {
            System.out.println("Доход выше верхней границы");
            return false;
        }
        return true;
    }

    private static int calculateFixedCharges()                                       //метод суммирующий фиксированные доходы
    {
        return officeRentCharge +
                telephonyCharge +
                internetAccessCharge +
                assistantSalary +
                financeManagerSalary;
    }
}
