import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class ActionsTest {
    private static ChromeDriver driver;

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\dgotl\\Downloads\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://dgotlieb.github.io/RelativeLocator/index.html");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void test01_doubleClick() {
        WebElement buttonElement = driver.findElement(By.id("myButton"));
        Actions myAction = new Actions(driver);
        myAction.doubleClick(buttonElement);
        myAction.perform();
        removeFocus();
    }

    @Test
    public void test02_selectMultipleTest() throws InterruptedException {
        WebElement buttonElement = driver.findElement(By.id("myButton"));
        Actions myAction = new Actions(driver);
        Thread.sleep(2000);
        myAction.clickAndHold(buttonElement);
        myAction.build().perform();
        removeFocus();
    }

    @Test
    public void test03_mouseHoverTest() throws InterruptedException {
        WebElement buttonElement = driver.findElement(By.id("myButton"));
        Actions myAction = new Actions(driver);
        Thread.sleep(2000);
        myAction.moveToElement(buttonElement);
        myAction.build().perform();
        removeFocus();
    }

    @Test
    public void test04_scrollToBottomElement() throws InterruptedException {
        Thread.sleep(2000);
        WebElement element = driver.findElement(By.id("bottom"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Test
    public void test05_sendKeyboardKeys() throws InterruptedException {
        Thread.sleep(2000);
        WebElement element = driver.findElement(By.id("bottom"));
        element.sendKeys(Keys.SEMICOLON);
    }

    // simply press another element to move focus from button
    private void removeFocus(){
        driver.findElement(By.tagName("input")).click();
    }

    @AfterClass
    public static void afterClass() {
        //driver.quit();
    }
}
