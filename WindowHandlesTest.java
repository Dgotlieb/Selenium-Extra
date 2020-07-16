import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowHandlesTest {
    private static WebDriver driver;
    private String googleTranslateHandle = "";

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\dgotl\\Downloads\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://translate.google.com");
    }

    @Test
    public void test01_openGoogleInANewTab(){
        googleTranslateHandle = driver.getWindowHandle(); // get google translate handle
        System.out.println("Window handle for Google Translate saved: " + googleTranslateHandle);
        ((JavascriptExecutor)driver).executeScript("window.open('https://google.com','_blank');");
    }

    @Test
    public void test02_goBackToTranslateUsingSavedHandle() throws InterruptedException {
        Thread.sleep(2000);
        driver.switchTo().window(googleTranslateHandle);
    }

    @Test
    public void test03_printALlHandles() {
        System.out.println(driver.getWindowHandles());
    }

    // todo //////////////////////////////////////////////////////////////////////////////////
    // todo //// Thread.sleep is bad practice and is here only so we can watch the actions ///
    // todo //////////////////////////////////////////////////////////////////////////////////

    @AfterClass
    public static void afterClass() {
        driver.quit();
    }
}
