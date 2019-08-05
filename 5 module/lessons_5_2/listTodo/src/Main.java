import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> listTodo = new ArrayList<>();
        Pattern pattern = Pattern.compile("([A-Za-z]{3,6})\\s?(\\d*)\\s?([А-Яа-я\\w\\s!?:;.\\-,]*)");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.printf("Введите пожалуйста команду, индекс в списке и наименование дела" +
                    " или одну только команду через пробел.%nДопустимые для ввода: LIST," +
                    " ADD, DELETE, EDIT.%n");
            String str = scanner.nextLine();
            Matcher matcher = pattern.matcher(str);

            if (matcher.find()) {
                String nameTeam = matcher.group(1).trim();                      //присвоение команды
                int numPosition = 0;
                if (matcher.group(2).length() > 0) {
                    numPosition = Integer.parseInt(matcher.group(2).trim());    //присвоение номера списка
                    if (numPosition > listTodo.size()) {
                        System.err.printf("Количество дел в списке %d. Введенный номер дела " +
                                "должен быть больше либо равным 0 и меньше либо равным " +
                                "количеству дел в списке%n.", listTodo.size());
                        continue;
                    }
                }

                String nameList = matcher.group(3).trim();                  //присвоение наименования дела
                if (nameList.length() > 0) {
                    if (nameTeam.equalsIgnoreCase("ADD")) {
                        if (matcher.group(2).length() == 0) {
                            listTodo.add(nameList);
                        } else if (matcher.group(2).length() > 0) {
                            listTodo.add(numPosition, nameList);
                        }
                    } else if (nameTeam.equalsIgnoreCase("EDIT")) {
                        listTodo.set(numPosition, nameList);
                    } else {
                        System.err.println("Команды LIST и DELETE вводятся без наименования " +
                                "дела или введенная команда не предусмотрена.");
                    }
                }

                if (nameList.length() == 0) {
                    if (nameTeam.equalsIgnoreCase("DELETE")) {
                        listTodo.remove(numPosition);
                    } else if (nameTeam.equalsIgnoreCase("LIST")) {
                        if (matcher.group(2).length() == 0) {
                            for (int i = 0; i < listTodo.size(); i++) {
                                System.out.printf("Дело № %d. Наименование дела: %s.%n",
                                        i, listTodo.get(i));
                            }
                            System.out.printf("%nВывод списка дел завершен.%n");
                            break;
                        } else if (matcher.group(2).length() > 0) {
                            System.out.println("Для вывода списка дел введите пожалуйста только" +
                                    " команду LIST");
                        }
                    } else {
                        System.err.println("Команды ADD и EDIT вводятся c наименованием " +
                                "дела или введенная команда не предусмотрена.");
                    }
                }
            } else if (!matcher.find()) {
                System.err.printf("Повторите пожалуйста ввод команды, индекс и наименования " +
                        "дела согласно примеру.%nLIST%nADD Какое-то дело%nADD 4 Какое-то дело " +
                        "на четвертом месте%nEDIT 3 Новое название дела%nDELETE 7%n");
            }
        }
    }
}