import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String pathFile = "movementList.csv";
        BankAccount bankAccount = new BankAccount(parseFile(pathFile));

        System.out.printf("Сумма всех приходов по счету %.2f руб.%n",
                bankAccount.sumComingOnAccount());
        System.out.printf("Сумма всех расходов по счету %.2f руб.%n%n",
                bankAccount.sumConsumption());
        bankAccount.printTransactionInformation();
    }

    private static List<String[]> parseFile(String file) {
        List<String[]> list = null;
        try(FileReader fileReader = new FileReader(file);
            CSVReader reader = new CSVReader(fileReader)) {
            list = reader.readAll();
        } catch (CsvException | IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}