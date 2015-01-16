package interactions;

import pages.nonStaticPages.PageObject;
import pages.nonStaticPages.SimplePageObject;
import pages.staticPages.SimpleStaticPageObject;
import pages.staticPages.StaticPageObject;
import junit.framework.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class InteractionsTest {
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
    public void keysTest() throws InterruptedException {
        driver.findElement(By.linkText("Key Presses")).click();
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Actions actions = new Actions(driver);
        for (int i = 0; i < letters.length(); i++) {
            String letter = String.valueOf(letters.charAt(i));
            actions.sendKeys(letter).perform();
            Thread.sleep(200);
            WebElement result = driver.findElement(By.id("result"));
            Assert.assertTrue(result.getText().contains(letter));
        }
    }

    @Test
    public void hoverTest() throws InterruptedException {
        driver.findElement(By.linkText("Hovers")).click();
        List<WebElement> figureCaption = driver.findElements(By.cssSelector(".figcaption"));
        for (WebElement caption : figureCaption) {
            Assert.assertFalse(caption.isDisplayed());
        }
        List<WebElement> figures = driver.findElements(By.cssSelector(".figure"));
        Actions actions = new Actions(driver);
        for (WebElement figure : figures) {
            actions.moveToElement(figure).perform();
            Assert.assertTrue(figure.findElement(By.tagName("h5")).isDisplayed());
            Thread.sleep(2000);
        }
    }

    @Test
    public void contextClickTest() throws InterruptedException {
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

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "You selected a context menu");
        alert.accept();
        Assert.assertFalse(isAlertPresent(driver));

    }

    @Test
    public void nestedFrameTest() throws InterruptedException {

        driver.findElement(By.linkText("Frames")).click();
        driver.findElement(By.linkText("Nested Frames")).click();
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        Assert.assertTrue(driver.getPageSource().contains("LEFT"));
        driver.switchTo().defaultContent();
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-middle");
        Assert.assertTrue(driver.getPageSource().contains("MIDDLE"));
        driver.switchTo().defaultContent();
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-right");
        Assert.assertTrue(driver.getPageSource().contains("RIGHT"));
        driver.switchTo().defaultContent();
        driver.switchTo().frame("frame-bottom");
        Assert.assertTrue(driver.getPageSource().contains("BOTTOM"));
    }

    @Test
    public void iFrameTest() throws InterruptedException {
        driver.findElement(By.linkText("Frames")).click();
        driver.findElement(By.linkText("iFrame")).click();
        driver.switchTo().frame("mce_0_ifr");
        WebElement editor = driver.findElement(By.id("tinymce"));
        editor.clear();
        editor.sendKeys("Hello World");
        Thread.sleep(3000);
        Assert.assertEquals("Hello World", editor.getText());
    }

    @Test
    public void dragNDropTest() throws InterruptedException {
        driver.findElement(By.linkText("Drag and Drop")).click();
        Thread.sleep(5000);
        WebElement columnA = driver.findElement(By.id("column-a"));
        WebElement columnB = driver.findElement(By.id("column-b"));
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(columnA, columnB.getLocation().getX() - columnA.getLocation().getX(), 0);
//        actions.dragAndDrop(columnA, columnB).perform();
        Assert.assertEquals(columnA.getText(), "B");
        Assert.assertEquals(columnB.getText(), "A");

//        actions.moveToElement(columnA).clickAndHold().perform();
//        actions.dragAndDrop(columnA, columnB).perform();
        //var 1
//        actions.dragAndDrop(columnA, columnB).perform();
//        //var 2
//        actions
//                .moveToElement(columnA)
//                .clickAndHold()
//                .moveToElement(columnB)
//                .release()
//                .perform();
//        actions.clickAndHold(columnA).perform();
//        Thread.sleep(1000);
//        actions.moveToElement(columnB).perform();
//        Thread.sleep(500);
//        actions.release().perform();
//        //var 3
//        actions.dragAndDropBy(columnA, 5, 5);
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
