import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class ElementsTest {

        private static final String BASE_SITE = "http://the-internet.herokuapp.com";
        private WebDriver driver;



        @BeforeMethod
        public void setup ()throws InterruptedException {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(4, TimeUnit.SECONDS);
            driver.get(BASE_SITE);

        }
        @AfterMethod
        public void teardown () {
            driver.quit();
        }

        @Test
        public void checkboxTest() throws InterruptedException {
            WebElement ref = driver.findElement(By.linkText("Checkboxes"));
            ref.click();
            List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type=checkbox]"));
//            System.out.println("First checkbox, value = " + checkboxes.get(0).isSelected());
//            System.out.println("Second checkbox, value = " + checkboxes.get(1).isSelected());
            checkboxes.get(0).click();
            checkboxes.get(1).click();
            Assert.assertTrue(checkboxes.get(0).isSelected());
            Assert.assertFalse(checkboxes.get(1).isSelected());
        }
    @Test
    public void buttonTest() throws InterruptedException {
        WebElement ref = driver.findElement(By.linkText("Checkboxes"));
        ref.click();
    }
    @Test
    public void dynamicControlsTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement ref = driver.findElement(By.linkText("Dynamic Controls"));
        ref.click();
        WebElement checkbox = driver.findElement(By.cssSelector("input[type=checkbox]"));
        WebElement div = driver.findElement(By.cssSelector("div#checkbox"));
        checkbox.click();
        Assert.assertEquals(div.getText().trim(), "A checkbox");
        WebElement removeButton = driver.findElement(By.id("btn"));
        removeButton.click();

//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
        Assert.assertEquals("Add", removeButton.getText());
        Assert.assertTrue(driver.getPageSource().contains("It's gone!"));
//        checkbox.click(); //нужно либо дописать аннотацию про эксепшн о несуществовании объекта, либо написать метод для обработки

//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[type=checkbox]")));

    }
    @Test
    public void positiveLoginTest() throws InterruptedException {

        WebElement ref = driver.findElement(By.linkText("Form Authentication"));
        ref.click();
        WebElement login = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement button = driver.findElement(By.cssSelector("[type=submit]"));
        login.sendKeys("tomsmith");
        password.sendKeys("SuperSecretPassword!");
        button.click();
        Assert.assertTrue(driver.getPageSource().contains("You logged into a secure area!"));
//        Assert.assertEquals(driver.getCurrentUrl(), "http://the-internet.herokuapp.com/secure");

    }
    @Test
    public void negativeLoginTest() throws InterruptedException {

        WebElement ref = driver.findElement(By.linkText("Form Authentication"));
        ref.click();
        WebElement login = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement button = driver.findElement(By.cssSelector("[type=submit]"));
        login.sendKeys("tomsmith1");
        password.sendKeys("SuperSecretPassword");
        button.click();
//        Assert.assertTrue(driver.getPageSource().contains("Your username is invalid!"));
        Assert.assertEquals(driver.getCurrentUrl(), "http://the-internet.herokuapp.com/login");

    }
    @Test
    public void dropdownTest() throws InterruptedException {
        WebElement ref = driver.findElement(By.linkText("Dropdown"));
        ref.click();
        Select dropdown = new Select(driver.findElement(By.id("dropdown")));
        dropdown.selectByValue("2");//selectByVisibleText("Option 1");


    }
}
