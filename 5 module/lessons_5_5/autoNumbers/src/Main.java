import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static int sizeListNumbers = 4000000;
    private static int regions = 197;
    private static int countWithoutRegions = sizeListNumbers / regions;
    private static ArrayList<String> listAutoNumbers = new ArrayList<>(sizeListNumbers);

    public static void main(String[] args) {
        generationNumbers();
        for (int i = 0; i < countWithoutRegions; i++) {
            addRegion(listAutoNumbers.get(i));
        }
        listAutoNumbers.subList(0, countWithoutRegions).clear();
        System.out.printf("Количество автомобильных номеров в базе %d%n", listAutoNumbers.size());

        for(;;) {
            String number = inputNumberForSearch();
            searchConsistent(number);                                           //последовательный поиск номера
            searchBinary(number);                                               //бинарный поиск номера

            HashSet<String> hashSet = new HashSet<>(listAutoNumbers);
            searchHashSet(hashSet, number);                                      //поиск по Хэшу

            TreeSet<String> treeSet = new TreeSet<>(listAutoNumbers);
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
        Pattern pattern = Pattern.compile("^[АВЕКМНОРСТХУ]\\d{3}[АВЕКМНОРСТХУ]{2}\\s\\d{2,3}");
        Scanner scanner = new Scanner(System.in);
        String number;
        while (true) {
            System.out.printf("%nВведите пожалуйста автомобильный номер для поиска. " +
                    "Между номером и регионом должен быть пробел.%n");
            number = scanner.nextLine().toUpperCase();
            Matcher matcher = pattern.matcher(number);
            if (matcher.find()) {
                break;
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
        while (listAutoNumbers.size() < countWithoutRegions) {
            String number = getNumberAuto();
            if (isThievesNumber(number)) {
                if (!listAutoNumbers.contains(number)) {
                    listAutoNumbers.add(number);
                }
            }
        }
        System.out.printf("Генерация блатных номеров завершена.%n");
    }

    private static void addRegion(String number) {
        for (int i = 1; i <= regions; i++) {
            String text;
            if (i < 10) {
                text = number + " " + i;
                text = text.replace(" ", " 0");
            } else {
                text = number + " " + i;
            }
            listAutoNumbers.add(text);
        }

    }

    private static void searchConsistent(String number) {
        long start = System.currentTimeMillis();
        if (listAutoNumbers.contains(number)) {
            long ducation = System.currentTimeMillis() - start;
            System.out.printf("Поиск автомобильного номера прямым перебором занял %dмс.%n", ducation);
        } else {
            System.out.println("Введенный номер не является блатным или отсутствует в списке.");
        }
    }

    private static void searchBinary (String number) {
        Collections.sort(listAutoNumbers);
        long start = System.currentTimeMillis();
        int index = Collections.binarySearch(listAutoNumbers, number);
        if (index >= 0) {
            long ducation = System.currentTimeMillis() - start;
            System.out.printf("Поиск автомобильного номера бинарным поиском занял %dмс.%n", ducation);
        } else {
            System.out.println("Введенный номер не является блатным или отсутствует в списке.");
        }
    }

    private static void searchHashSet(HashSet<String> hashSet, String number) {
        long start = System.currentTimeMillis();
        if (hashSet.contains(number)) {
            long ducation = System.currentTimeMillis() - start;
            System.out.printf("Поиск автомобильного номера по ХЭШу занял %dмс.%n", ducation);
        } else {
            System.out.println("Введенный номер не является блатным или отсутствует в списке.");
        }
    }

    private static void searchTreeSet(TreeSet<String> treeSet, String number) {
        long start = System.currentTimeMillis();
        if (treeSet.contains(number)) {
            long ducation = System.currentTimeMillis() - start;
            System.out.printf("Поиск автомобильного номера по упорядоченному ХЭШу занял %dмс.%n",
                    ducation);
        } else {
            System.out.println("Введенный номер не является блатным или отсутствует в списке.");
        }
    }
}