import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static TreeMap<String, String> phoneBook = new TreeMap<String, String>();

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("^(?<comand>[A-Z]{3,4})(?<phoneNumber>\\s.{10,})?$");

        while (true) {
            System.out.printf("Если хотите добавить контакт в телефонную книгу, введите команду " +
                    "ADD и далее через пробел телефонный номер в любом формате%n" +
                    "Если хотите просмотреть весь список контактов, введите команду LIST%n" +
                    "Для завершения работы программы введите EXIT%n");
            String text = scanner.nextLine();
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                String comand = matcher.group("comand").trim();
                if (!isComand(comand)) {
                    comand = repeatComand();
                }

                boolean exit = false;
                switch (comand) {
                    case "LIST":
                        outputAllContact(phoneBook);
                        break;
                    case "EXIT":
                        exit = true;
                        break;
                    case "ADD":
                        String phone = matcher.group("phoneNumber").trim();
                        phone = formatPhoneNumber(phone);
                        if (isPhoneFound(phoneBook, phone)) {
                            infoAboutContact(phoneBook, phone);
                        } else if (!isPhoneFound(phoneBook, phone)) {
                            String nameUser = askUserForName();
                            if (isNameFound(phoneBook, nameUser)) {
                                infoAboutContact(phoneBook, nameUser);
                            } else {
                                phoneBook.put(nameUser, phone);
                                System.out.printf("Контакт добавлен.%n");
                            }
                        }
                        break;
                }
                if (exit) {
                    break;
                }
            } else {
                System.err.printf("Строка введена не верно.%n");
            }
        }
    }

    private static void outputAllContact(TreeMap<String, String> map) {
        int countContact = 0;
        for (Map.Entry<String, String> pair : map.entrySet()) {
            countContact++;
            String key = pair.getKey();
            String value = pair.getValue();
            System.out.printf("--------------------------------------------------%n" +
                            "%d %s =>> %s%n--------------------------------------------------%n",
                    countContact, key, value);
        }
    }

    private static void infoAboutContact(TreeMap<String, String> map, String text) {
        for (Map.Entry<String, String> pair : map.entrySet()) {
            String key = pair.getKey();
            String value = pair.getValue();
            if (text.equals(key) || text.equals(value)) {
                System.out.printf("==================================================%n" +
                        "Сведения о существующем контакте:%n%s =>> %s%n" +
                        "==================================================%n", key, value);
            }
        }
    }

    private static boolean isComand(String comand) {
        boolean text1 = false;
        if (comand.equals("ADD")) {
            text1 = true;
        } else if (comand.equals("LIST")) {
            text1 = true;
        } else if (comand.equals("EXIT")) {
            text1 = true;
        } else {
            System.err.println("Введенной команды нет в списке допустимых команд." +
                    "Повторите ввод команды заново.");
        }
        return text1;
    }

    private static boolean isPhoneFound(TreeMap<String, String> map, String phone) {
        boolean isPhone = false;
        if (map.containsValue(phone)) {
            System.err.println("В телефонной книге уже присутствует контакт с таким же номером.");
            isPhone = true;
        }
        return isPhone;
    }

    private static boolean isNameFound(TreeMap<String, String> map, String nameUser) {
        boolean isName = false;
        if (map.containsKey(nameUser)) {
            System.err.println("В телефонной книге уже присутствует контакт с таким же именем.");
            isName = true;
        }
        return isName;
    }

    private static String formatPhoneNumber(String NumberPhone) {
        while (true) {
            NumberPhone = NumberPhone.replaceAll("[^0-9]+", "");
            Character firstSymbol = NumberPhone.charAt(0);
            if (firstSymbol.equals('8')) {
                NumberPhone = NumberPhone.replaceFirst("8", "7");
            }
            Pattern patternNum = Pattern.compile("^(?<allNumberPhone>(?<codeCountry>\\d)" +
                    "(?<codeOperatora>\\d{3})(?<numPhone>\\d{7}))$");
            Matcher matcher = patternNum.matcher(NumberPhone);
            if (!matcher.find()) {
                System.err.printf("Номер телефона состоит: %d цифра обозначающая страну " +
                        "%d цифры обозначающие оператора связи, %d цифр номер телефона.%n", 1, 3, 7);
                NumberPhone = repeatPhoneNumber();
            } else {
                break;
            }
        }
        return NumberPhone;
    }

    private static String repeatComand() {
        String text;
        do {
            System.out.println("Повторите ввод команды. Введите доступную команду из списку:" +
                    "ADD, LIST или EXIT.");
            text = scanner.nextLine().toUpperCase();
        } while (!isComand(text));
        return text;
    }

    private static String repeatPhoneNumber() {
        System.out.println("Введите номер телефона повторно, в любом формате.");
        String number = scanner.nextLine();
        return number;
    }

    private static String askUserForName() {
        Pattern patternName = Pattern.compile("^(?<name>[\\dА-Яа-яёй]{2,15})\\s?" +
                "(?<partonymic>[\\dА-Яа-яёй]{2,15})?\\s?(?<surname>[\\dА-Яа-яёй]{2,15})?$");
        String text;
        System.out.printf("Введите наименование контакта. Фрагменты имя, отчество и фамилия " +
                "могут занимать от 2 до 15 символов каждая.%nИмя должно быть обязательным.%n");
        while (true) {
            text = scanner.nextLine().trim();
            Matcher matcherName = patternName.matcher(text);
            if (matcherName.find()) {
                break;
            } else {
                System.err.printf("В наименовании контакта могут присутствовать только " +
                        "следующие символы: A-Za-zА-Яа-яёй0-9_.%n Повторите ввод заново.%n%n");
            }
        }
        return text;
    }
}