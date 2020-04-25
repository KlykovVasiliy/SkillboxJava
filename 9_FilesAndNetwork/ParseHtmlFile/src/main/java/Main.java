import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        String webSite = "https://lenta.ru";
        Document doc = Jsoup.connect(webSite).maxBodySize(0).get();
        Document document = Jsoup.parse(doc.html());
        Elements elements = document.select("img.g-picture");

        System.out.println("Список абсолютных путей изображений с сайта https://lenta.ru/\n");
        for (String string : getListAbsPathImg(elements)) {
            System.out.println(string);
        }

        System.out.println("\nСкачивание изображений началось.");
        System.out.printf("Для скачивания доступно %d изображений%n", getListAbsPathImg(elements).size());
        for (String string : getListAbsPathImg(elements)) {
            downloadImage(string);
        }
        System.out.println("Скачивание изображений завершено.");
    }

    public static List<String> getListAbsPathImg(Elements elements) {
        return elements.stream()
                .map(e -> e.attr("abs:src"))
                .collect(Collectors.toList());
    }

    public static void downloadImage(String imageUrl) {
        String nameImage = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        try (InputStream in = new URL(imageUrl).openStream()) {
            String dir = "data";
            Files.copy(in, Paths.get(dir, nameImage), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}