package TestMethods;

import org.json.JSONObject;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.testng.ITestResult;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static TestMethods.SetUpFixture.startDate;
import static java.lang.Math.abs;


@SuppressWarnings("Annotator")
public class StatusResult {

    public static String mailBody;
    public static String testState;
    private static Integer successTest = 0, failTest = 0, skipTest = 0;
    private static List<String> FailureTest = new ArrayList<>();
    private static List<String> SuccessTest = new ArrayList<>();
    private static List<String> SkippTest = new ArrayList<>();
    @Rule
    public final TestName name;

    {
        name = new TestName();
    }

    public String getTestName()
    {
        System.out.println(new TestName().getMethodName());
        return  new TestName().getMethodName();
    }
    /**
     * @param result get TEST RESULT write dropt(FAIL,SKIP) tests to DB
     *               SKIP,FAIL -> RTFM
     * @throws SQLException throw all SQL exception from
     */
    @SuppressWarnings("Annotator")


    public  String TestStatus(ITestResult result, Method method) throws Exception{
        WriteLog.testName = method.getName();
        long currentDate = new Date().getTime();
        long second = abs((startDate - currentDate)) / 1000;
        String milisecond = Long.toString(second * 1000 - (abs((startDate - currentDate))));
        String diff = Long.toString(second) + " sec. " + milisecond + " mSec.";
        switch (result.getStatus()) {
            case ITestResult.FAILURE: {
                testState = "FAILURE";
                WriteLog.TestLog("TEST RESULT:FAILED");
                failTest = failTest + 1;
                FailureTest.add(WriteLog.testName + "-FAILED - Test Time:" + diff + "\n");
                break;
            }
            case ITestResult.SUCCESS: {
                testState = "SUCCESS";
                WriteLog.TestLog("TEST RESULT:PASS");
                successTest = successTest + 1;
                SuccessTest.add(WriteLog.testName + "-PASSED- Test Time:" + diff + "\n");
                break;
            }
            case ITestResult.SKIP: {
                testState = "SKIP";
                WriteLog.TestLog("TEST RESULT:SKIP");
                skipTest = skipTest + 1;
                SkippTest.add(WriteLog.testName + "-SKIPPED- Test Time:" + diff + "\n");
                break;
            }
        }

        SetUpFixture startTest = new SetUpFixture();
        startTest.EndDate();
        WriteLog.TestLog(" Pass Test:" + successTest + "\t Failure Test: " + failTest + "\t Skipp Test: " + skipTest);
        return mailBody;
    }
}
