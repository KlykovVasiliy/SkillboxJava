import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static double score = 0.00;

    public static void main(String[] args) {
        Pattern patternStr = Pattern.compile("^(?<command>[\\w]+)(?<money>\\s[\\d\\.\\,]+)?$");
        System.out.printf("Доступные команды и их обозначения:%nADD - пополнить счёт%n" +
                "BALANCE - отображение баланса счёта%nDELETE - снятие денег со счёта%nВведите " +
                "команду и сумму которую нужно добавить на счет или снять со счёта или одну " +
                "только команду.%n");

        while (true) {
            System.out.println("Введите строку");
            String str = scanner.nextLine();
            Matcher matcherStr = patternStr.matcher(str);
            if (matcherStr.find()) {
                String command = matcherStr.group("command");
                if(!isCommand(command)) {
                    command = inputCommand();
                }
                Optional<String> optionalMoney = Optional.ofNullable(matcherStr.group("money"));
                double money;

                boolean exit = false;
                switch(command) {
                    case "ADD":
                        money = getMoneyOfOptional(optionalMoney);
                        addMoney(money);
                        break;
                    case "BALANCE":
                        outputBalance();
                        break;
                    case "DELETE":
                        money = getMoneyOfOptional(optionalMoney);
                        deleteMoney(money);
                        break;
                    case "EXIT":
                        exit = true;
                        break;
                }
                if (exit) {
                    break;
                }
            } else {
                System.err.println("Строка введена не верно. Повторите ввод.");
            }
        }
    }

    private static String inputCommand() {
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

    private static double getMoneyOfOptional(Optional<String> optionalMoney) {
        String strMoney = optionalMoney.orElseGet(Main::inputAmountOfMoney).trim();
        if (!isMoneyFormat(strMoney)) {
            strMoney = inputAmountOfMoney();
        }
        double money = Double.parseDouble(strMoney);
        return money;
    }

    private static String inputAmountOfMoney() {
        String text;
        System.out.println("Введите сумму в денежном формате.");
        while(true) {
            text = scanner.nextLine();
            if (!isMoneyFormat(text)) {
                System.err.println("Введена строка не в денежном формате, повторите ввод суммы.");
                continue;
            }
            text = text.replace(",",".");
            break;
        }
        return text;
    }

    private static boolean isMoneyFormat (String money) {
        Pattern pattern = Pattern.compile("^\\d+(\\.[\\d]{0,2})*$");
        Matcher matcher = pattern.matcher(money);
        boolean isMoney = matcher.find();
        return isMoney;
    }

    private static void addMoney(double money) {
        score += money;
        System.out.printf("На ваш счёт поступило %.02f рубль(ей).%n", score);
        outputBalance();
    }

    private static void deleteMoney (double money) {              //снятие со счета
        if (money <= score) {
            score -= money;
            outputBalance();
        } else {
            System.out.printf("На расчетном счету недостаточно денежных средств для снятия.%n" +
                    "Баланс вашего счета составляет %.02f%n", score);
        }
    }

    private static void outputBalance () {                           //вывод информации о балансе
        System.out.printf("Баланс вашего счёта составляет %.02f рубля(ей)%n", score);
    }
}