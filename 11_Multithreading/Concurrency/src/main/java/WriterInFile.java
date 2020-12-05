import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class WriterInFile {
    private final Path pathFile = Paths.get("src/main/resources/mapSite.txt");

    public WriterInFile(List<String> listPages) {
        List<String> listPagesSite = listPages;
        listPagesSite = listPagesSite.stream()
                .sorted()
                .map(this::addTabToTheBeginningOfTheLine)
                .collect(Collectors.toList());
        writeToFile(listPagesSite);
    }

    private void writeToFile(List<String> listPagesSite) {
        try {
            Files.write(pathFile, listPagesSite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String addTabToTheBeginningOfTheLine(String str) {
        StringBuilder builder = new StringBuilder(str);
        int countTab = getCountSlash(str) - 2;
        for (int i = 0; i < countTab; i++) {
            builder.insert(0, "\t");
        }
        return builder.toString();
    }

    private int getCountSlash(String url) {
        char[] chars = url.toCharArray();
        int countSlash = 0;
        for (char ch : chars) {
            if (ch == '/') {
                countSlash++;
            }
        }
        return countSlash;
    }
}