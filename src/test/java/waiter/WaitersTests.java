package waiter;


import helpers.Waiter;
import junit.framework.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class WaitersTests {
    private static final String BASE_SITE = "http://the-internet.herokuapp.com";
    private WebDriver driver;


    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(4, TimeUnit.SECONDS);
        driver.get(BASE_SITE);

    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void waitForAlertTest() throws InterruptedException {
        driver.findElement(By.linkText("Context Menu")).click();
        WebElement box = driver.findElement(By.id("hot-spot"));
        Actions actions = new Actions(driver);
        actions.contextClick(box).perform();
        actions
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .perform();

        Waiter.waitForAlert(driver, "You selected a context menu");
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "You selected a context menu");
        alert.accept();
        Assert.assertFalse(isAlertPresent(driver));

    }



    @Test
    public void waitForTitleTest() {
        String title = driver.getTitle();
        Waiter.waitForTitle(driver, "The Internet");
    }

    @Test
    public void waitForHandleCountMoreThanOneTest() throws InterruptedException {
        driver.get("http://www.w3schools.com/html/html5_draganddrop.asp");
        WebElement tryItButton = driver.findElement(By.cssSelector(".tryitbtn"));
        tryItButton.click();
        Waiter.waitForHandleCountMoreThanOne(driver);
        driver.close();
    }

    @Test
    public void waitForHandleCountMoreThanTest() throws InterruptedException {
        driver.get("http://www.w3schools.com/html/html5_draganddrop.asp");
        WebElement tryItButton = driver.findElement(By.cssSelector(".tryitbtn"));
        tryItButton.click();
        driver.get("http://www.w3schools.com/html/html5_draganddrop.asp");
        tryItButton = driver.findElement(By.cssSelector(".tryitbtn"));
        tryItButton.click();
        Waiter.waitForHandleCountMoreThan(driver, 2);
        driver.close();
    }

    @Test
    public void waitForElementIsClickable() throws InterruptedException {
        driver.get("http://usefulscript.ru/disabled_button.php");
        WebElement login = driver.findElement(By.cssSelector("input[name=login]"));
        WebElement password = driver.findElement(By.cssSelector("input[name=pass]"));
        login.sendKeys("adaf");
        password.sendKeys("adad");
        Waiter.waitForElementIsClickable(driver, By.cssSelector("input[name=start]"));
        driver.close();
    }


    public static boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

}
