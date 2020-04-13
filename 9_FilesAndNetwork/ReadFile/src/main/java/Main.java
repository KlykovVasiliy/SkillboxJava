import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String sourcePathStr;
        String endPathStr;
//        Path path = Paths.get("C:\\Users\\Vasiliy\\Desktop");
        Path path = Paths.get("F:\\IT\\Git");
        long sizeInBytes = 0;
        double sizeInKBytes = 0;
        double sizeInMBytes = 0;
        double sizeInGBytes = 0;
        try {
            sizeInBytes = Files.walk(path).map(m -> m.toFile().length())
                    .reduce((long) 0, Long::sum);
            sizeInKBytes = sizeInBytes / 1024.0;
            sizeInMBytes = sizeInKBytes / 1024.0;
            sizeInGBytes = sizeInMBytes / 1024.0;
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        System.out.printf("%d байт%n", sizeInBytes);
        System.out.printf("%.2f Кбайт%n", sizeInKBytes);
        System.out.printf("%.2f МГбайт%n", sizeInMBytes);
        System.out.printf("%.2f Гбайт%n", sizeInGBytes);
    }
}