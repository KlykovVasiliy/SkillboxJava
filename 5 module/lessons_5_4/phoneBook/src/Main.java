import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        TreeMap<String, String> phoneBook = new TreeMap<String, String>();
        Pattern pattern = Pattern.compile("^[A-Z]{3,4}$");
        String team;

        while (true) {
            System.out.printf("Если хотите добавить контакт в телефонную книгу, введите команду " +
                    "ADD%nЕсли хотите просмотреть весь список контактов, введите команду LIST%n" +
                    "Для завершения работы программы введите EXIT%n");
            team = scanner.nextLine();
            Matcher matcher = pattern.matcher(team);

            if (matcher.find()) {
                boolean exit = false;
                switch (team) {
                    case "LIST":
                        outputAllContact(phoneBook);
                        break;
                    case "EXIT":
                        exit = true;
                        break;
                    case "ADD":
                        String name = nameContact();
                        String phone = phoneNumber();
                        if (isKeyValue(phoneBook, name, phone)) {
                            infoAboutContact(phoneBook, name, phone);
                        } else {
                            phoneBook.put(name, phone);
                        }
                        break;
                }
                if (exit) {
                    break;
                }
            } else {
                System.err.println("Команда введена не верно. Повторите ввод.");
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

    private static void infoAboutContact(TreeMap<String, String> map, String name, String phone) {
        for (Map.Entry<String, String> pair : map.entrySet()) {
            String key = pair.getKey();
            String value = pair.getValue();
            if (name.equals(key) || phone.equals(value)) {
                System.out.printf("==================================================%n" +
                        "Сведения о существующем контакте:%n%s =>> %s%n" +
                        "==================================================%n", key, value);
            }
        }
    }

    private static boolean isKeyValue(TreeMap<String, String> map, String name, String phone) {
        boolean nameAndKey = false;
        if (map.containsKey(name)) {
            System.err.println("В телефонной книге уже присутствует контакт с таким же именем.");
            nameAndKey = true;
        } else if (map.containsValue(phone)) {
            System.err.println("В телефонной книге уже присутствует контакт с таким же номером.");
            nameAndKey = true;
        }
        return nameAndKey;
    }

    private static String phoneNumber() {
        Pattern patternNum = Pattern.compile("^(?<codeCountry>\\+\\d)\\s" +
                "(?<codeOperatora>\\d{3})\\s(?<numPhone>\\d{7})$");
        String number;
        System.out.println("Введите телефонный номер в едином формате: +0 123 4567890");

        while (true) {
            number = scanner.nextLine();
            Matcher matcherNum = patternNum.matcher(number);
            if (matcherNum.find()) {
                break;
            } else {
                System.err.println("Телефонный номер введен и неверном формате." +
                        "Повторите ввод телефонного номера.");
            }
        }
        return number;
    }

    private static String nameContact() {
        Pattern patternName = Pattern.compile("^(?<name>[\\dА-Яа-я]{2,15})\\s?" +
                "(?<partonymic>[\\dА-Яа-я]{2,15})?\\s?(?<surname>[\\dА-Яа-я]{2,15})?$");
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
                        "следующие символы: A-Za-zА-Яа-я0-9_.%n Повторите ввод заново.%n%n");
            }
        }
        return text;
    }
}