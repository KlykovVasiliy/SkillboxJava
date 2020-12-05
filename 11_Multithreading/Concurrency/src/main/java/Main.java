import java.util.concurrent.ForkJoinPool;

public class Main {
    public static String homePageSite;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println("Программа для создания карты сайта запущена.");
        homePageSite = "https://skillbox.ru";

        var root = new PageSite(homePageSite);
        var links = new ForkJoinPool().invoke(root);
        new WriterInFile(links);

        System.out.printf("Программа завершена. Время выполнения программы: %d мс%n",
                System.currentTimeMillis() - startTime);
        System.out.printf("Найдено %d ссылок.%n", links.size());
    }
}
