import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void operation(ArrayList<String> list, String functionList, String text) {
        if (functionList.equalsIgnoreCase("ADD")) {
            list.add(text);
        }
    }

    public static void operationList(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("Дело № %d. Наименование дела: %s.%n", i, list.get(i));
        }
    }

    public static void operationWithNum(ArrayList<String> list, String functionList, int numPos, String text) {
        if (numPos <= list.size()) {
            if (functionList.equalsIgnoreCase("ADD")) {
                list.add(numPos, text);
            } else if (functionList.equalsIgnoreCase("EDIT")) {
                list.set(numPos, text);
            } else if (functionList.equalsIgnoreCase("DELETE")) {
                list.remove(numPos);
            }
        } else if (numPos > list.size()) {
            System.err.printf("Введённый индекс наименования дела превышает текущий размер списка.%n" +
                    "Повторите ввод установив индекс меньше равный %d и меньше.%n", list.size());
        }
    }

    public static void main(String[] args) throws IOException {

        ArrayList<String> listTodo = new ArrayList<>();
        Pattern patternStr = Pattern.compile("^[A-Za-z]{3,6}[\\s]?[\\d\\w]*[\\s]*[\\w\\sА-Яа-я!?.,:;]*$");
        Pattern patternComand = Pattern.compile("^[A-Za-z]{3,6}$");
        Pattern patternComandNum = Pattern.compile("^[A-Za-z]{3,6}[\\s]+[\\d]+$");
        Pattern patternComandNumTodo = Pattern.compile("^[A-Za-z]{3,6}[\\s]+[\\d]+[\\s]+[\\w\\sА-Яа-я!?.,:;]+$");
        Pattern patternComandTodo = Pattern.compile("^[A-Za-z]{3,6}[\\s]+[\\w\\sА-Яа-я!?.,:;]+$");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.printf("Введите пожалуйста команду, индекс в списке и наименование дела" +
                    " через пробел.%nКоманды допустимые для ввода: LIST, ADD, DELETE, EDIT.%n");

            String str = reader.readLine();
            String function = null;
            String num = null;
            String text = "";

            Matcher matcherStr = patternStr.matcher(str);
            Matcher matcherComandNum = patternComandNum.matcher(str);
            Matcher matcherComandNumTodo = patternComandNumTodo.matcher(str);
            Matcher matcherComandTodo = patternComandTodo.matcher(str);
            Matcher matcherComand = patternComand.matcher(str);
            boolean matchesStr = matcherStr.matches();
            boolean matchesNum = matcherComandNum.matches();
            boolean matchesComandNumTodo = matcherComandNumTodo.matches();
            boolean matchesComandTodo = matcherComandTodo.matches();
            boolean matchesComand = matcherComand.matches();

            if (matchesStr) {
                if (matchesComand) {                                                //команда без параметров
                    if (str.equalsIgnoreCase("LIST")) {
                        operationList(listTodo);
                    } else {
                        System.err.println("Без указания параметров предусмотрена только команда " +
                                "LIST. Повторите ввод.");
                        continue;
                    }
                } else if (matchesNum) {                                            //команда с номером индекса списка
                    function = str.substring(0, str.indexOf(" ")).trim();
                    num = str.substring(str.indexOf(" ")).trim();
                    int numPosition = Integer.parseInt(num);
                    operationWithNum(listTodo, function, numPosition, text);
                } else if (matchesComandNumTodo) {                                  //команда номер индекс имя дела
                    function = str.substring(0, str.indexOf(" ")).trim();
                    String textAndNum = str.substring(str.indexOf(" ")).trim();
                    num = textAndNum.substring(0, textAndNum.indexOf(" ")).trim();
                    text = textAndNum.substring(textAndNum.indexOf(" ")).trim();
                    int numPosition = Integer.parseInt(num);
                    operationWithNum(listTodo, function, numPosition, text);
                } else if (matchesComandTodo) {                                     //команда и имя дела
                    function = str.substring(0, str.indexOf(" ")).trim();
                    text = str.substring(str.indexOf(" ")).trim();
                    operation(listTodo, function, text);
                }
            } else {
                System.err.printf("Команда введена неверно или такая команда не предусмотрена.%n" +
                        "Пожалуйста повторите ввод с учётом перечисленных команд.%n");
            }
        }
    }
}
/*
Хочу сократить участок кода с 76 по 90ую строки, т.к. очень много код дублируется.
Есть идея создать метод который проверенную уже регулярными выражениями строку будет распределять
по фрагментам назначения: команда, индекс списка и наименование дела. Загвоздка в том что не пойму
как осуществить возврат от 2х до 3х фрагментов с содержимым значением. Подскажите пожалуйста как
сократить код.
 */