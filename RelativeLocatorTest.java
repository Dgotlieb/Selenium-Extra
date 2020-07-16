import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

public class RelativeLocatorTest {
    private static ChromeDriver driver;

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\dgotl\\Downloads\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://dgotlieb.github.io/RelativeLocator/index.html");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void aboveTest() {
        driver.findElement(withTagName("input").above(By.id("myButton"))).sendKeys("above");
    }

    @Test
    public void belowTest() {
        driver.findElement(withTagName("input").below(By.id("myButton"))).sendKeys("below");
    }

    @Test
    public void rightOFTest() {
        driver.findElement(withTagName("input").toRightOf(By.id("myButton"))).sendKeys("rightOf");
    }

    @AfterClass
    public static void afterClass() {
        driver.quit();
    }
}
