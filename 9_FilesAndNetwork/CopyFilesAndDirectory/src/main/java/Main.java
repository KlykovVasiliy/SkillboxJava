import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("folderSrc");
        Path pathDestination = Paths.get("folderDestination" + File.separator + "copy");
        Files.walkFileTree(path, new CopyFileVisitor(path, pathDestination));
    }
}
