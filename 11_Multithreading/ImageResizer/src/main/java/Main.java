import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    private static ThreadPoolExecutor executor;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int coresCount = Runtime.getRuntime().availableProcessors();
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(coresCount);

        String srcFolder = "src/main/resources/srcFolder/";
        String dstFolder = "src/main/resources/dstFolder/";
        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();

        if (files != null) {
            resizeFiles(files, dstFolder, start);
        } else {
            System.out.println("Folder is empty.");
        }
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\nОбщее время выполнения (мс): " +
                (System.currentTimeMillis() - start));
    }

    private static void resizeFiles(File[] files, String dstFolder, long start) {
        int newWidth = 300;

        for (File file : files) {
            executor.execute(new ImageResizer(file, newWidth, dstFolder, start));
        }
        executor.shutdown();
    }
}