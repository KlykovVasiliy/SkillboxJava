package accounts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckingAccount {
    private static Scanner scanner = new Scanner(System.in);

    private double score = 0.00;

    protected double getScore() {
        return score;
    }

    protected void setScore(double score) {
        this.score = score;
    }

    public static String inputCommand() {
        System.out.printf("Доступные команды и обозначения:%nDEPOSIT - пополнить счёт%n" +
                "BALANCE - отображение баланса счёта%nWITHDRAW - снятие денег со счёта%n" +
                "EXIT - завершение операций со счётом%nВведите нужную команду:%n");
        Pattern pattern = Pattern.compile("^[A-Z]{3,8}$");
        String text;
        while(true) {
            text = scanner.nextLine();
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                if (isCommand(text)) {
                    break;
                } else {
                    System.err.println("Введенная команда не предусмотрена, повторите ввод");
                }
            } else {
                System.err.println("Команда введена не верно, повторите ввод");
            }
        }
        return text;
    }

    private static boolean isCommand(String command) {
        String[] list = {"DEPOSIT", "BALANCE", "WITHDRAW", "EXIT"};
        ArrayList<String> listCommand = new ArrayList<>(Arrays.asList(list));
        return listCommand.contains(command);
    }

    public double inputAmountOfMoney() {
        String text;
        System.out.println("Введите сумму в денежном формате.");
        while(true) {
            text = scanner.nextLine();
            text = text.replace(",",".");
            if (!isMoneyFormat(text)) {
                System.err.println("Введена строка не в денежном формате, повторите ввод суммы.");
                continue;
            }
            break;
        }
        return Double.parseDouble(text);
    }

    private static boolean isMoneyFormat (String money) {
        Pattern pattern = Pattern.compile("^\\d+(\\.[\\d]{0,2})*$");
        Matcher matcher = pattern.matcher(money);
        return matcher.find();
    }

    public boolean isDeposit(double money) {
        double moneyStart = getScore();
        setScore(getScore() + money);
        boolean isSuccess = false;
        if (moneyStart < getScore()) {
            isSuccess = true;
            System.out.printf("На ваш счёт поступило %.02f рубль(ей).%n", money);
        } else {
            System.err.println("Не удалось пополнить счёт.");
        }
        balance();
        return isSuccess;
    }

    public boolean isWithdraw (double money) {              //снятие со счета
        boolean isSuccess = false;
        if (money <= getScore()) {
            isSuccess = true;
            setScore(getScore() - money);
            System.out.printf("С вашего счёта было снято %.02f рубля(ей)%n", money);
            balance();
        } else {
            System.out.printf("На расчетном счету недостаточно денежных средств для снятия.%n" +
                    "Баланс вашего счета составляет %.02f%n", getScore());
        }
        return isSuccess;
    }

    public void balance () {                           //вывод информации о балансе
        System.out.printf("Баланс вашего счёта составляет %.02f рубля(ей)%n", getScore());
    }
}