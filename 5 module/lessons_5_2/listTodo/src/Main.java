import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> listTodo = new ArrayList<>();
        Pattern pattern = Pattern.compile("([A-Z]{3,6})\\s?(?<indexList>\\d*)\\s?" +
                "(?<text>[А-Яа-я\\w\\s!?:;.\\-,]*)");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.printf("Введите пожалуйста команду, индекс в списке и наименование дела" +
                    " или одну только команду через пробел.%nДопустимые для ввода: ADD, EDIT," +
                    " LIST, DELETE, EXIT.%n");
            String str = scanner.nextLine();
            Matcher matcher = pattern.matcher(str);

            if (matcher.find()) {
                String nameTeam = matcher.group(1).trim();                      //присвоение команды
                Optional<Integer> position = Optional.of(matcher.group("indexList"))
                        .filter(num -> num.length() > 0).map(Integer::parseInt);
                Optional<String> nameCase = Optional.of(matcher.group("text"))
                        .filter(s -> s.length() > 0).map(String::toString);

                switch (nameTeam) {
                    case "ADD":
                        addToList(listTodo, position, nameCase);
                        break;
                    case "EDIT":
                        replaceListItem(listTodo, position, nameCase);
                        break;
                    case "LIST":
                        outputListItems(listTodo);
                        break;
                    case "DELETE":
                        deleteListItem(listTodo, position);
                        break;
                }
                if (nameTeam.equals("EXIT")) {
                    break;
                }
            } else if (!matcher.find()) {
                System.err.printf("Повторите пожалуйста ввод команды, индекс и наименования " +
                        "дела согласно примеру.%nADD Какое-то дело%nADD 4 Какое-то дело " +
                        "на четвертом месте%nEDIT 3 Новое название дела%nLIST%nDELETE 7%n");
            }
        }
    }

    public static void addToList(ArrayList<String> list, Optional<Integer> index, Optional<String> text) {
        String str = text.isPresent() ? text.get() : retypingText();
        if (index.isEmpty()) {
            list.add(str);
        }
        if (index.isPresent()) {
            int num = index.get();
            if (isNumberLessLimit(list, num)) {
                list.add(num, str);
            } else if (!isNumberLessLimit(list, num)) {
                num = retypingNum(list);
                list.add(num, str);
            }
        }
    }

    public static void replaceListItem(ArrayList<String> list, Optional<Integer> index, Optional<String> text) {
        String str = text.isPresent() ? text.get() : retypingText();
        int num = index.isPresent() ? index.get() : retypingNum(list);
        if (isNumberLessLimit(list, num)) {
            list.set(num, str);
        } else if (!isNumberLessLimit(list, num)) {
            num = retypingNum(list);
            list.set(num, str);
        }
    }

    public static void outputListItems(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("Дело № %d. Наименование дела: %s.%n",
                    i, list.get(i));
        }
        System.out.printf("==============================================================%n" +
                "Вывод списка дел завершен.%n");
    }

    public static void deleteListItem(ArrayList<String> list, Optional<Integer> index) {
        int num = index.isPresent() ? index.get() : retypingNum(list);
        if (isNumberLessLimit(list, num)) {
            list.remove(num);
        } else if (!isNumberLessLimit(list, num)) {
            num = retypingNum(list);
            list.remove(num);
        }
    }

    public static boolean isNumberLessLimit(ArrayList<String> list, int indexItem) {
        boolean numberLess = false;
        if (indexItem <= list.size() && indexItem >= 0) {
            numberLess = true;
        }
        return numberLess;
    }

    public static String retypingText() {
        Scanner scanner = new Scanner(System.in);
        String text;
        while (true) {
            System.out.println("Наименование дела не может быть пустым. Пожалуйста повторите ввод.");
            text = scanner.nextLine();
            if (text.length() > 0) {
                break;
            }
        }
        return text;
    }

    public static int retypingNum(ArrayList<String> list) {
        String numStr;
        int num = list.size() + 1;
        Pattern pat = Pattern.compile("[\\d]+");
        Scanner scanner = new Scanner(System.in);
        while (num < 0 || num > list.size()) {
            System.err.printf("Количество дел в списке %d. Введенный номер дела " +
                    "должен быть больше либо равным 0 и меньше либо равным " +
                    "%d. Введите пожалуйста только цифру.%n", list.size(), list.size() - 1);
            numStr = scanner.nextLine();
            Matcher mat = pat.matcher(numStr);
            if (mat.find()) {
                num = Integer.parseInt(numStr);
            }
        }
        return num;
    }
}