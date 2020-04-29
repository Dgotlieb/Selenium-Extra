import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Report {
    private static ChromeDriver driver;
    // create ExtentReports and attach reporter(s)
    private static ExtentReports extent ;
    // creates a toggle for the given test, adds all log events under it
    private static ExtentTest test ;

    @BeforeClass
    public static void beforeClass() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("C:\\Users\\dgotl\\IdeaProjects\\relativelocator\\extent.html");
        // attach reporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        // name your test and add description
        test = extent.createTest("MyFirstTest", "Sample description");
        // add custom system info
        extent.setSystemInfo("Environment", "Production");
        extent.setSystemInfo("Tester", "Daniel");
        // log results
        test.log(Status.INFO, "@Before class");

        boolean driverEstablish = false;
        try {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\dgotl\\Downloads\\chromedriver\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driverEstablish = true;
        } catch (Exception e) {
            e.printStackTrace();
            fail("Cant connect chromeDriver");
            test.log(Status.FATAL, "Driver Connection Failed! " + e.getMessage());
            driverEstablish = false;
        } finally {
            if (driverEstablish) {
                test.log(Status.PASS, "Driver established successfully");
            }
        }
    }

    @Test
    public void test1_openPage() {
        boolean pageOpened = false;
        try {
            driver.get("https://translate.google.com/");
            String firstWindowString = driver.getWindowHandle();
            System.out.println("Window String: " + firstWindowString);
            pageOpened = true;

        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, "Google Translate page was not found " + e.getMessage());
            pageOpened = false;
        } finally {
            if (pageOpened) {
                test.log(Status.PASS, "Open webpage " + "Webpage opened successfully");
            }
        }
    }

    @Test
    public void test2_clickTextField() throws IOException {
        boolean pressed = false;
        try {
            driver.findElement(By.id("source")).click();
            pressed = true;
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, "Translate box was not clicked " + e.getMessage());
            pressed = false;
        } finally {
            if (pressed) {
                test.log(Status.PASS, "Translate box click " + "Translate button was clicked successfully");
            }
        }
    }

    @Test
    public void numberExceptionTest(){
        try {
            int a = 1 / 0;
        } catch (ArithmeticException e) {
            test.log(Status.FAIL, "NumberException " + e.getMessage());
        }
    }

    @AfterClass
    public static void afterClass() {
        test.log(Status.INFO, "@After test " + "After test method");
        driver.quit();
        // build and flush report
        extent.flush();
    }
}