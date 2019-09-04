import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static int sizeListNumbers = 4000000;
    private static int regions = 197;
    private static int countWithoutRegions = sizeListNumbers / regions;
    private static ArrayList<String> listAutoNumbers = new ArrayList<>(sizeListNumbers);
    private static HashSet<String> setWithoutRegions = new HashSet<>(countWithoutRegions);

    public static void main(String[] args) {
        generationNumbers();
        for (String number : setWithoutRegions) {
            addRegion(number);
        }
        Collections.sort(listAutoNumbers);
        System.out.printf("Количество автомобильных номеров в базе: %d%n", listAutoNumbers.size());

        String number;
        HashSet<String> hashSet = new HashSet<>(listAutoNumbers);
        TreeSet<String> treeSet = new TreeSet<>(listAutoNumbers);
        for(;;) {
            number = inputNumberForSearch();
            System.out.printf("Количество автомобильных номеров в списке: %d%n", listAutoNumbers.size());
            searchConsistent(number);                                           //последовательный поиск номера
            System.out.printf("Количество автомобильных номеров в списке: %d%n", listAutoNumbers.size());
            searchBinary(number);                                               //бинарный поиск номера

            System.out.printf("%nКоличество автомобильных номеров во множестве: %d%n", hashSet.size());
            searchHashSet(hashSet, number);                                      //поиск по Хэшу

            System.out.printf("%nКоличество автомобильных номеров во множестве: %d%n", treeSet.size());
            searchTreeSet(treeSet, number);                                      //поиск по Хэш дереву
        }
    }

    private static String getNumberAuto() {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();

        String letForNumber = "АВЕКМНОРСТХУ";
        String digNumber = "0123456789";
        int autoNumLength = 6;
        for (int i = 0; i < autoNumLength; i++) {
            if (i == 0 || i == 4 || i == 5) {
                int number = random.nextInt(letForNumber.length());
                builder.append(letForNumber.charAt(number));
            } else {
                int number = random.nextInt(digNumber.length());
                builder.append(digNumber.charAt(number));
            }
        }
        return builder.toString();
    }

    private static String inputNumberForSearch() {
        Pattern pattern = Pattern.compile("^[АВЕКМНОРСТХУ]\\d{3}[АВЕКМНОРСТХУ]{2}\\s\\d{2,3}$");
        Scanner scanner = new Scanner(System.in);
        String number;
        while (true) {
            System.out.printf("%nВведите пожалуйста автомобильный номер для поиска. " +
                    "Между номером и регионом должен быть пробел.%n");
            number = scanner.nextLine().toUpperCase();
            Matcher matcher = pattern.matcher(number);
            if (matcher.find()) {
                String str = number.substring(0, number.indexOf(" ")).trim();
                if (!setWithoutRegions.contains(str)) {
                    System.out.println("Такого номера нет в списке. Повторите ввод.");
                } else {
                    break;
                }
            }
        }
        return number;
    }

    private static boolean isThievesNumber (String text) {
        String[] list = {"ААА", "АММ", "АМО", "АМР", "ВМР", "ВОО", "ЕКХ", "ЕРЕ", "ККХ", "ТМР",
                "КМР", "КОО", "МММ", "ММР", "МОО", "ООО", "РМР", "САС", "СОО", "ССС", "ХКХ"};
        List<String> listThievesNumbers = Arrays.asList(list);
        String letters = text.replaceAll("[^АВЕКМНОРСТХУ]", "");
        return listThievesNumbers.contains(letters);
    }

    private static void generationNumbers() {
        System.out.println("Генерация блатных номеров начата.");
        while (setWithoutRegions.size() < countWithoutRegions) {
            String number = getNumberAuto();
            if (isThievesNumber(number)) {
                setWithoutRegions.add(number);
            }
        }
        System.out.printf("Генерация блатных номеров завершена.%n");
    }

    private static void addRegion(String number) {
        for (int i = 1; i <= regions; i++) {
            String text = String.format("%s %02d", number, i);
            listAutoNumbers.add(text);
        }
    }

    private static void searchConsistent(String number) {
        long start = System.nanoTime();
        if (listAutoNumbers.contains(number)) {
            long ducation = System.nanoTime() - start;
            System.out.printf("Автомобильный номер найден прямым перебором за %dнс.%n"
                    , ducation);
        }
    }

    private static void searchBinary (String number) {
        long start = System.nanoTime();
        int index = Collections.binarySearch(listAutoNumbers, number);
        long ducation = System.nanoTime() - start;
        System.out.printf("Автомобильный номер %s найден бинарным поиском за %dнс.%n"
                , listAutoNumbers.get(index), ducation);
    }

    private static void searchHashSet(HashSet<String> hashSet, String number) {
        long start = System.nanoTime();
        if (hashSet.contains(number)) {
            long ducation = System.nanoTime() - start;
            System.out.printf("Поиск автомобильного номера по ХЭШу занял %dнс.%n", ducation);
        }
    }

    private static void searchTreeSet(TreeSet<String> treeSet, String number) {
        long start = System.nanoTime();
        if (treeSet.contains(number)) {
            long ducation = System.nanoTime() - start;
            System.out.printf("Поиск автомобильного номера по упорядоченному множеству занял %dнс.%n",
                    ducation);
        }
    }
}