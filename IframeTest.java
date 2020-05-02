import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IframeTest {
    private static WebDriver driver;

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\dgotl\\Downloads\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("C:/Users/dgotl/Desktop/iFrames.html");
    }

    @Test
    public void test01_pressAlert() throws InterruptedException {
        driver.findElement(By.id("alert")).click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
    }

    @Test
    public void test02_tryIframeBeforeSwitching() {
        System.out.println("We know it will fail...");
        driver.findElement(By.id("iframe_alert")).click();
    }

    @Test
    public void test03_switchToIframe() {
        System.out.println("Switching to iFrame...");
        driver.switchTo().frame("my_frame");
        driver.findElement(By.id("iframe_alert")).click();
        driver.switchTo().alert().accept();
    }

    @Test
    public void test03_switchBack() throws InterruptedException {
        System.out.println("Switching back...");
        driver.switchTo().defaultContent();
        driver.findElement(By.id("alert")).click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
    }

    // todo //////////////////////////////////////////////////////////////////////////////////
    // todo //// Thread.sleep is bad practice and is here only so we can watch the actions ///
    // todo //////////////////////////////////////////////////////////////////////////////////

    @AfterClass
    public static void afterClass() {
        driver.quit();
    }
}
