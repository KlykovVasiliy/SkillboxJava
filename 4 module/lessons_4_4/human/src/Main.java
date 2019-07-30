import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) throws IOException {
        Pattern pattern = Pattern.compile("^([А-Я]([а-я])+[\\s]?){2,3}");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите Фамилию, имя и отчество чувака");

        while (true) {
            String text = reader.readLine();
            String fragments[] = text.split("\\s");
            Matcher matcher = pattern.matcher(text);
            boolean matches = matcher.matches();
            if (matches) {
                if (fragments.length >= 2) {
                    System.out.println("Фамилия: " + fragments[0]);
                    System.out.println("Имя: " + fragments[1]);
                }
                if (fragments.length == 3) {
                    System.out.println("Отчество " + fragments[2]);
                }
                break;
            } else if (!matches) {
                System.err.println("Введите строку состоящую из русских букв, каждое слово" +
                        " начинается с прописной буквы, слова должны быть разделены пробелом.");
            }
        }
    }
}