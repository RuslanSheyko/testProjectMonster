import Driver.InitialDrivers;
import Mapping.MainMapping;
import TestMethods.AdditionalMethods;
import TestMethods.StatusResult;
import TestMethods.WriteLog;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static Driver.InitialDrivers.driver;

public class Main {


        @BeforeMethod
        public void Start(Method method) throws Exception {
            WriteLog.testName = method.getName();
            InitialDrivers initialDrivers = new InitialDrivers();
            initialDrivers.selectDriver();

        }

        @AfterMethod(alwaysRun = true)
        public void StopTest(ITestResult result, Method method) throws Exception {
            new StatusResult().TestStatus(result, method);
            driver.quit();

        }

        @Test
        public  void VerifyMail() throws Exception
        {
            String email= "test"+ new AdditionalMethods().randomGenerate(50000,1)+"@test.com";
            Assert.assertTrue(new MainMapping().email(email,"test42141").equals("PLEASE CHECK YOUR EMAIL"));
        }

        @Test
        public  void VerifyExistMail() throws Exception
        {
            Assert.assertTrue(new MainMapping().existEmail("test@tes.com","test42141").equals("test@tes.com"));
        }
        @Test
        @Parameters({"pass","finalTxt"})
        public  void VerifyBadPass(String pass,String finalTxt) throws Exception
        {
            String email= "test"+ new AdditionalMethods().randomGenerate(50000,1)+"@test.com";
            Assert.assertTrue(new MainMapping().badPass(email,pass).equals(finalTxt));
        }
        @Test
        public  void VerifyForgotPass() throws Exception
        {
            String email= "test"+ new AdditionalMethods().randomGenerate(50000,1)+"@test.com";
            Assert.assertTrue( new MainMapping().forgotPass(email,"").contains("Oops..."));
        }
    @Test
    public  void VerifyFaceBook() throws Exception
    {
        String email= "test"+ new AdditionalMethods().randomGenerate(50000,1)+"@test.com";
        Assert.assertTrue( new MainMapping().facebookEnter(email,"").contains("Неверный электронный адрес"));
    }
}
