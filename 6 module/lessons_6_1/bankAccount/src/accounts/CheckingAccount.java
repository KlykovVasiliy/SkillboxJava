package accounts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckingAccount {
    public static Scanner scanner = new Scanner(System.in);

    private static double score = 0.00;


    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        CheckingAccount.score = score;
    }

    public static String inputCommand() {
        System.out.printf("Доступные команды и обозначения:%nADD - пополнить счёт%n" +
                "BALANCE - отображение баланса счёта%nDELETE - снятие денег со счёта%n" +
                "EXIT - завершение операций со счётом%nВведите нужную команду:%n");
        Pattern pattern = Pattern.compile("^[A-Z]{3,7}$");
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
        String[] list = {"ADD", "BALANCE", "DELETE", "EXIT"};
        ArrayList<String> listCommand = new ArrayList<>(Arrays.asList(list));
        return listCommand.contains(command);
    }

    public static String inputAmountOfMoney() {
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
        return text;
    }

    private static boolean isMoneyFormat (String money) {
        Pattern pattern = Pattern.compile("^\\d+(\\.[\\d]{0,2})*$");
        Matcher matcher = pattern.matcher(money);
        return matcher.find();
    }

    public void addMoney(double money) {
        setScore(getScore() + money);
        System.out.printf("На ваш счёт поступило %.02f рубль(ей).%n", money);
        outputBalance();
    }

    public void deleteMoney (double money) {              //снятие со счета
        if (money <= getScore()) {
            setScore(getScore() - money);
            outputBalance();
        } else {
            System.out.printf("На расчетном счету недостаточно денежных средств для снятия.%n" +
                    "Баланс вашего счета составляет %.02f%n", getScore());
        }
    }

    public void outputBalance () {                           //вывод информации о балансе
        System.out.printf("Баланс вашего счёта составляет %.02f рубля(ей)%n", getScore());
    }
}
