import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите пожалуйста номер телефона в любом формате.");
        String phoneNumber = reader.readLine();

        phoneNumber = phoneNumber.replaceAll("[^0-9]+", "");
        Character startNumber = phoneNumber.charAt(0);
        if (startNumber.equals('8')) {
            phoneNumber = phoneNumber.replaceFirst("8", "7");
        }
        System.out.println(phoneNumber);
    }
}
