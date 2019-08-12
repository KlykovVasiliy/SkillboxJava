import java.util.Iterator;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        TreeSet<String> listEmail = new TreeSet<String>();
        Scanner scanner = new Scanner(System.in);
        Pattern patString = Pattern.compile("(?<team>[A-Z]{3,4})\\s?(?<nameEmail>\\w{2,10}@" +
                "[a-z]{2,7}\\.[a-z]{2,3})?");
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

    public static String inputRepeatEmail() {
        Pattern patEmail = Pattern.compile("(\\w{2,10}@[a-z]{2,7}\\.[a-z]{2,3}){1,1}");
        Scanner scanner = new Scanner(System.in);
        String email;

        while (true) {
            System.out.printf("Введите электронную почту для регистрации.%n");
            email = scanner.nextLine().trim();
            Matcher matEmail = patEmail.matcher(email);
            if (matEmail.find()) {
                break;
            } else {
                System.err.println("Электронная почта введена неверно. Повторите ввод.");
            }
        }
        return email;
    }

    public static void addToEmail(TreeSet<String> set, Optional<String> optional) {
        String str = optional.orElseGet(Main::inputRepeatEmail);
        if (set.contains(str)) {
            System.out.println("Такая почта уже зарегистрирована. Повторите пожалуйста ввод.");
            str = inputRepeatEmail();
        } else {
            set.add(str);
        }
    }

    public static void outputListEmail(TreeSet<String> set) {
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String text = iterator.next();
            System.out.println(text);
        }
    }
}