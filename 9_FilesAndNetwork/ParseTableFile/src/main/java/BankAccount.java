import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BankAccount {
    @Getter
    private List<BankAccountString> listOfOperations = new ArrayList<>();

    public BankAccount(List<String[]> listArrayStr) {
        loadBankAccountFromFile(listArrayStr);
    }

    private void loadBankAccountFromFile(List<String[]> list) {
        for (int i = 1; i < list.size(); i++) {
            String[] line = list.get(i);
            if (line.length != 8) {
                System.out.printf("Линия %d не корректна:%n%s%n", i + 1, Arrays.toString(line));
                continue;
            }
            listOfOperations.add(new BankAccountString(
                    trimStrDescriptionOperation(line[5]),
                    (Double.parseDouble(line[6].replace(',', '.'))),
                    (Double.parseDouble(line[7].replace(',', '.')))
            ));
        }
    }

    private String trimStrDescriptionOperation(String string) {
        String spaceSplit = "   ";
        string = string.substring(string.indexOf(spaceSplit), string.lastIndexOf(spaceSplit))
                .trim();
        return string.substring(0, string.lastIndexOf(spaceSplit)).trim();
    }

    public void printTransactionInformation() {
        listOfOperations.stream()
                .collect(Collectors.groupingBy(BankAccountString::getOperationDescription,
                        Collectors.mapping(Summary::fromTransaction,
                                Collectors.reducing(Summary::merge)
                        )))
                .forEach((s, sum) -> System.out.printf("%s:\t" +
                                "(приход: %.2fруб.\t-\tрасход: %.2fруб.)%n",
                        s, sum.get().income, sum.get().withdraw));
    }

    public double sumComingOnAccount() {
        return listOfOperations.stream()
                .map(x -> x.coming)
                .reduce(0.0, Double::sum);
    }

    public double sumConsumption() {
        return listOfOperations.stream()
                .map(x -> x.consumption)
                .reduce(0.0, Double::sum);
    }

    private static class Summary {
        double income;
        double withdraw;

        Summary(double income, double withdraw) {
            this.income = income;
            this.withdraw = withdraw;
        }

        static Summary merge(Summary s1, Summary s2) {
            return new Summary(s1.income + s2.income, s1.withdraw + s2.withdraw);
        }

        static Summary fromTransaction(BankAccountString t) {
            return new Summary(t.getComing(), t.getConsumption());
        }
    }
}