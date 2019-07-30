import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        LocalDate dateOfBirth = LocalDate.of(1992, 05, 31);
        LocalDate dateNow = LocalDate.now();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.yyyy - E", Locale.ENGLISH);

        int myAge = dateNow.getYear() - dateOfBirth.getYear();
        for (int age = 0; age <= myAge; age++) {
            if (dateOfBirth.isBefore(dateNow)) {
                System.out.println(age + " - " + dateOfBirth.format(formatDate));
                dateOfBirth = dateOfBirth.plusYears(1);
            } else if (dateOfBirth.isAfter(dateNow)) {
                System.out.println("Сегодня: " + dateNow.format(formatDate) +
                        " и вам всё ещё " + age);
                break;
            }
        }
    }
}