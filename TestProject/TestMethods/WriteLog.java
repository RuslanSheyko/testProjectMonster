package TestMethods;

import org.apache.commons.lang3.time.DateFormatUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class WriteLog {

    public static String testName = "";

    /**
     * Delete LogFile if it more than 50Mb
     */
    private static void DeleteFileLog(String newPath) {
        File file = new File(newPath);
        if (file.length() > 5048576) {
            if (file.delete()) {
                System.out.println(newPath + " была найдена c корневой директории проекта");
            } else
                System.out.println(newPath + " не была найдена в корневой директории проекта или не является пустой");
        }
    }

    /**
     * //Create TestLog if it miss or add logTxt to log file if it exist
     *
     * @param logText= your tex to log file
     */
    public static void TestLog(String logText) {
        String path = System.getProperty("user.dir");
        String newPath = path + "\\Resources\\TestLog\\TestLog.txt";
        DeleteFileLog(newPath);
        WriteTxt(newPath, logText);
    }


    /**
     * @param newPath your path to log file
     * @param logText your log text
     */
    private static void WriteTxt(String newPath, String logText) {

        try {
            Date date = new Date();
            FileWriter writer = new FileWriter(newPath, true);
            writer.append(DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss")).append("\t").append(SetUpFixture.sessionID).append("\t").append(testName).append("\t").append(logText);
            // запись всей строки
            writer.append("\r\n");
            writer.flush();
        } catch (Exception ex) {
         }
    }

    ///Add to log file first Date with session and browser
    public static void FirstTimeLog() {
        String path = System.getProperty("user.dir");
        String newPath = path + "\\..\\TestLog\\TestLog.txt";
        try {
            FileWriter writer = new FileWriter(newPath, true);
            writer.append("\t" + "DATE/TIME" + "\t" + "SESSION ID" + "\t" + "BROWSER" + "\t" + "  TEST NAME" + "\t" + "  DESCRIPTION" + "\r\n");
            writer.flush();
            writer.close();
        } catch (IOException ex) {
        }

    }

    public void createDirectory(String directoryName) {
        try {
            Files.createDirectories(Paths.get(directoryName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
