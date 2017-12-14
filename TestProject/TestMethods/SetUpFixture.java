package TestMethods;

import Driver.InitialDrivers;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;


/**
 * Start Before all test to initial driver
 */
public class SetUpFixture {

    public static String sessionID;
    public static long startDate;

    public String StartSession() throws Exception {
        StartDate();
        if (InitialDrivers.sessionID != null)
            return InitialDrivers.sessionID;
        Date date = new Date();
        startDate = new Date().getTime();
        String time = (DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:SS"));
        time = time.replaceAll("[-: ]", "");
        WriteLog.FirstTimeLog();
        sessionID = time.substring(0, 14);
        return sessionID;
    }

    protected void StartDate() {
        long startDateMs = System.currentTimeMillis();
        Date date = new Date();
        String time = (DateFormatUtils.format(date, "HH:mm:SS"));
        WriteLog.FirstTimeLog();
        WriteLog.TestLog("Start TEST TIME:" + "\t" + time);
    }

    protected long EndDate() throws Exception {
        Date date = new Date();
        String time = (DateFormatUtils.format(date, "HH:mm:SS"));
        long currentTime = (System.currentTimeMillis()/* - startDateMs*/) / 1000;
        WriteLog.TestLog("End TEST TIME:" + "\t" + currentTime + "s.");
        return currentTime;
    }


}
