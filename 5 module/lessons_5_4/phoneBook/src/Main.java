import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static TreeMap<String, String> phoneBook = new TreeMap<String, String>();

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("(?<phoneNumber>\\s?[\\s\\d\\+\\(\\)\\-]{10,17})?" +
                "(?<nameContactOrComand>\\s?[\\s\\wА-Яа-яёй]{2,47})?");

        while (true) {
            System.out.printf("Если хотите добавить контакт в телефонную книгу, введите " +
                    "телефонный номер в любом формате или наименование контакта%n" +
                    "Если хотите просмотреть весь список контактов, введите команду LIST%n" +
                    "Для завершения работы программы введите EXIT%n");
            String text = scanner.nextLine();
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                Optional<String> optionalPhoneNumber = Optional.ofNullable
                        (matcher.group("phoneNumber")).filter(num -> num.length() > 0);
                Optional<String> optionalNameContact = Optional.ofNullable
                        (matcher.group("nameContactOrComand")).filter(name -> name.length() > 0);

                String phoneNumber;
                String nameContact;
                if (optionalPhoneNumber.isPresent()) {
                    phoneNumber = formatPhoneNumber(text);
                    if (isPhoneFound(phoneNumber)) {
                        infoAboutContact(phoneNumber);
                    } else {
                        nameContact = repeatNameContact();
                        if (isNameFound(nameContact)) {
                            infoAboutContact(nameContact);
                        } else {
                            phoneBook.put(nameContact, phoneNumber);
                        }
                    }
                } else if (optionalNameContact.isPresent()) {
                    nameContact = optionalNameContact.get();
                    if (isNameFound(nameContact)) {
                        infoAboutContact(nameContact);
                    } else if (isComand(nameContact)) {
                        outputAllContact();
                    } else {
                        phoneNumber = formatPhoneNumber(repeatPhoneNumber());
                        if (isPhoneFound(phoneNumber)) {
                            infoAboutContact(phoneNumber);
                        } else {
                            phoneBook.put(nameContact, phoneNumber);
                        }
                    }
                }
            } else {
                System.err.printf("Строка введена не верно.%n");
            }
        }
    }

    private static void outputAllContact() {
        int countContact = 0;
        for (Map.Entry<String, String> pair : phoneBook.entrySet()) {
            countContact++;
            String key = pair.getKey();
            String value = pair.getValue();
            System.out.printf("--------------------------------------------------%n" +
                            "%d %s =>> %s%n--------------------------------------------------%n",
                    countContact, key, value);
        }
    }

    private static void infoAboutContact(String text) {
        for (Map.Entry<String, String> pair : phoneBook.entrySet()) {
            String key = pair.getKey();
            String value = pair.getValue();
            if (text.equals(key) || text.equals(value)) {
                System.out.printf("==================================================%n" +
                        "Сведения о существующем контакте:%n%s =>> %s%n" +
                        "==================================================%n", key, value);
            }
        }
    }

    private static boolean isPhoneFound(String phone) {
        if (phoneBook.containsValue(phone)) {
            System.err.println("В телефонной книге уже присутствует контакт с таким же номером.");
            return true;
        }
        return false;
    }

    private static boolean isNameFound(String nameUser) {
        boolean isName = false;
        if (phoneBook.containsKey(nameUser)) {
            System.err.println("В телефонной книге уже присутствует контакт с таким же именем.");
            isName = true;
        }
        return isName;
    }

    private static boolean isComand(String comand) {
        String[] list = {"LIST"};
        List<String> listComand = Arrays.asList(list);
        String comandTemp = comand.toUpperCase();
        return listComand.contains(comandTemp);
    }

    private static boolean isNameContact(String nameContact) {
        Pattern patternName = Pattern.compile("^(?<name>[\\wА-Яа-яёй]{2,15})\\s?" +
                "(?<partonymic>[\\wА-Яа-яёй]{2,15})?\\s?(?<surname>[\\wА-Яа-яёй]{2,15})?$");
        Matcher matcherName = patternName.matcher(nameContact);
        boolean isName = false;
        if (matcherName.find()) {
            isName = true;
        } else {
            System.err.printf("В наименовании контакта могут присутствовать только " +
                    "следующие символы: A-Za-zА-Яа-яёй0-9_.%n Повторите ввод заново.%n%n");
        }
        return isName;
    }

    private static String formatPhoneNumber(String numberPhone) {
        while (true) {
            numberPhone = numberPhone.replaceAll("[^0-9]+", "");
            Character firstSymbol = numberPhone.charAt(0);
            if (firstSymbol.equals('8')) {
                numberPhone = numberPhone.replaceFirst("8", "7");
            }
            Pattern patternNum = Pattern.compile("^(?<allNumberPhone>(?<codeCountry>\\d)" +
                    "(?<codeOperatora>\\d{3})(?<numPhone>\\d{7}))$");
            Matcher matcher = patternNum.matcher(numberPhone);
            if (!matcher.find()) {
                System.err.printf("Номер телефона состоит: %d цифра обозначающая страну " +
                        "%d цифры обозначающие оператора связи, %d цифр номер телефона.%n", 1, 3, 7);
                numberPhone = repeatPhoneNumber();
            } else {
                break;
            }
        }
        return numberPhone;
    }

    private static String repeatPhoneNumber() {
        System.out.println("Введите номер телефона повторно, в любом формате.");
        String number = scanner.nextLine();
        return number;
    }

    private static String repeatNameContact() {
        System.out.printf("Введите наименование контакта. Фрагменты имя, отчество и фамилия " +
                "могут занимать от 2 до 15 символов каждая.%nИмя должно быть обязательным.%n");
        String text;
        do {
            text = scanner.nextLine().trim();
        } while (!isNameContact(text));
        return text;
    }
}