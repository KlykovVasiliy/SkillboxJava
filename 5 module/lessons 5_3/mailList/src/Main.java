import java.util.Iterator;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        TreeSet<String> listEmail = new TreeSet<String>();
        Pattern patString = Pattern.compile("^(?<team>[A-Z]{3,4})\\s?(?<nameEmail>[\\w@\\.]+)?$");
        System.out.printf("ADD регистрация эл. почты в списке учета.%n" +
                "LIST вывод всего списка.%nEXIT завершение программы.%n" +
                "Для регистрации эл. почты в списке введите команду ADD " +
                "и далее через пробел эл. почту.%n");

        while (true) {
            System.out.printf("Введите команду и электронную почту которую хотите добавить " +
                    "или другую команду.%n");
            String emailStr = scanner.nextLine();
            Matcher matString = patString.matcher(emailStr);

            if (matString.find()) {
                String team = matString.group("team").trim();
                Optional<String> optional = Optional.ofNullable(matString.group("nameEmail"))
                        .filter(s -> s.length() > 0);

                boolean exit = false;
                switch (team) {
                    case "ADD":
                        addToEmail(listEmail, optional);
                        break;
                    case "LIST":
                        outputListEmail(listEmail);
                        break;
                    case "EXIT":
                        exit = true;
                        break;
                }
                if (exit) {
                    break;
                }
            } else {
                System.err.printf("Введены недопустимая команда или эл. почта не подходящая " +
                        "под условие. Повторите ввод заново.%n");
            }
        }
    }

    private static boolean isEmail (String str) {
        Pattern patEmail = Pattern.compile("^(\\w{2,10}@[a-z]{2,7}\\.[a-z]{2,3})$");
        Matcher matEmail = patEmail.matcher(str);
        boolean mailBool = false;
        if (matEmail.find()) {
            mailBool = true;
        }
        return mailBool;
    }

    private static String inputRepeatEmail() {
        String email;
        while (true) {
            System.out.printf("Введите электронную почту для регистрации. Без команды.%n");
            email = scanner.nextLine().trim();
            if (isEmail(email)) {
                break;
            } else {
                System.err.println("Электронная почта введена неверно. Повторите ввод.");
            }
        }
        return email;
    }

    private static void addToEmail(TreeSet<String> set, Optional<String> optional) {
        String str = optional.orElseGet(Main::inputRepeatEmail);
        while (true) {
            if (isEmail(str)) {
                if (!set.contains(str)) {
                    set.add(str);
                    break;
                } else {
                    System.out.println("Такая почта уже зарегистрирована. Повторите пожалуйста ввод.");
                    str = inputRepeatEmail();
                }
            } else {
                System.err.println("Не верный формат электронной почты. Повторите ввод");
                str = inputRepeatEmail();
            }
        }
    }

    private static void outputListEmail(TreeSet<String> set) {
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String text = iterator.next();
            System.out.println(text);
        }
    }
}