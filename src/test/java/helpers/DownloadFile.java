package helpers;

import java.io.File;

public class DownloadFile {
    public static boolean checkDownloadedFile() throws InterruptedException {
        String path = "C:\\Users\\User\\Downloads";
        Thread.sleep(5000);
        File dir = new File(path);
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.getName().contains("sampleFile")) {
                return true;
            }
        }
        return false;
    }
}
