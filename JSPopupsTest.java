import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class JSPopupsTest {
    private static WebDriver driver;

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\dgotl\\Downloads\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://dgotlieb.github.io/Selenium-Extra/js-popups.html");
    }

    @Test
    public void test01_handleAlert() throws InterruptedException {
        driver.findElement(By.id("alert")).click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
    }

    @Test
    public void test02_handleConfirm() throws InterruptedException {
        driver.findElement(By.id("confirm")).click();
        Thread.sleep(2000);
        driver.switchTo().alert().dismiss();
        Thread.sleep(2000);
    }

    @Test
    public void test03_handlePrompt() throws InterruptedException {
        driver.findElement(By.id("prompt")).click();
        Alert promptAlert = driver.switchTo().alert();
        Thread.sleep(2000);
        promptAlert.sendKeys("George");
        Thread.sleep(2000);
        promptAlert.accept();
        Thread.sleep(2000);
    }

    // todo //////////////////////////////////////////////////////////////////////////////////
    // todo //// Thread.sleep is bad practice and is here only so we can watch the actions ///
    // todo //////////////////////////////////////////////////////////////////////////////////

    @AfterClass
    public static void afterClass() {
        driver.quit();
    }
}
