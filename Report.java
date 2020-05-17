import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.testng.FileAssert.fail;

public class Report {
    private static ChromeDriver driver;
    // create ExtentReports and attach reporter(s)
    private static ExtentReports extent ;
    // creates a toggle for the given test, adds all log events under it
    private static ExtentTest test ;

    @BeforeClass
    public static void beforeClass() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("C:\\Users\\dgotl\\IdeaProjects\\TestNGMaven\\extent.html");
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
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\dgotl\\Downloads\\chrome\\chromedriver.exe");
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
            String timeNow = String.valueOf(System.currentTimeMillis());
            test.info("details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(timeNow)).build());

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

    private static String takeScreenShot(String ImagesPath) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath+".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath+".png";
    }
}
