import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {
    private static Path pathFile = Paths.get("src/main/resources/mapMoscow.json");

    public static void main(String[] args) {
        LineHandlerHTML handlerHTML = new LineHandlerHTML("PageFromWiki.html");

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(String.valueOf(pathFile)), handlerHTML);
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintOnConsole print = new PrintOnConsole();
        print.printCountStationByLines(pathFile);
    }
}