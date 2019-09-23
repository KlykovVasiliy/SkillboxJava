package accounts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckingAccount {
    private static Scanner scanner = new Scanner(System.in);
    private double balance = 0.00;

    public static String inputCommand() {
        System.out.printf("Доступные команды и обозначения:%nDEPOSIT - пополнить счёт%n" +
                "BALANCE - отображение баланса счёта%nWITHDRAW - снятие денег со счёта%n" +
                "EXIT - завершение операций со счётом%nВведите нужную команду:%n");
        Pattern pattern = Pattern.compile("^[A-Z]{3,8}$");
        String text;
        while (true) {
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
        while (true) {
            text = scanner.nextLine();
            text = text.replace(",", ".");
            if (!isMoneyFormat(text)) {
                System.err.println("Введена строка не в денежном формате, повторите ввод суммы.");
                continue;
            }
            break;
        }
        return Double.parseDouble(text);
    }

    private static boolean isMoneyFormat(String money) {
        Pattern pattern = Pattern.compile("^\\d+(\\.[\\d]{0,2})*$");
        Matcher matcher = pattern.matcher(money);
        return matcher.find();
    }

    public boolean deposit(double money) {
        if (money > 0) {
            balance += money;
            System.out.printf("На ваш счёт поступило %.02f рубль(ей).%n", money);
            printBalance();
            return true;
        } else {
            System.err.println("Не удалось пополнить счёт. Значение вносимых средств должно " +
                    "быть больше 0");
            printBalance();
            return false;
        }
    }

    public boolean withdraw(double money) {              //снятие со счета
        if (balance >= money && money >= 0) {
            balance -= money;
            System.out.printf("С вашего счёта было снято %.02f рубля(ей)%n", money);
            printBalance();
            return true;
        } else {
            System.err.printf("Вы не можете снять отрицательную сумму либо на расчетном счету " +
                            "недостаточно денежных средств для снятия.%nПопытались снять %.02f, " +
                            "а баланс вашего счета составляет %.02f%n", money,
                    balance);
            return false;
        }
    }

    public void printBalance() {                           //вывод информации о балансе
        System.out.printf("Баланс вашего счёта составляет %.02f рубля(ей)%n", balance);
    }
}