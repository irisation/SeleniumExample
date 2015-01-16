package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.nonStaticPages.PageObject;
import pages.nonStaticPages.SimplePageObject;
import pages.staticPages.SimpleStaticPageObject;
import pages.staticPages.StaticPageObject;

import java.util.concurrent.TimeUnit;


public class PageObjectsTest {
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
    public void loginSimpleStaticPOTest() {
        driver.findElement(By.linkText("Form Authentication")).click();
        driver.findElement(SimpleStaticPageObject.USERNAME).sendKeys("tomsmith");
        driver.findElement(SimpleStaticPageObject.PASSWORD).sendKeys("SuperSecretPassword!");
        driver.findElement(SimpleStaticPageObject.LOGIN_BUTTON).click();
    }

    @Test
    public void loginStaticPOTest() {
        driver.findElement(By.linkText("Form Authentication")).click();
        StaticPageObject.login(driver, "tomsmith", "SuperSecretPassword!");
    }

    @Test
    public void loginSimplePOTest() {
        driver.findElement(By.linkText("Form Authentication")).click();
        SimplePageObject simplePageObject = new SimplePageObject(driver);
        simplePageObject.getUsername().sendKeys("tomsmith");
        simplePageObject.getPassword().sendKeys("SuperSecretPassword!");
        simplePageObject.getLoginButton().click();
    }

    @Test
    public void loginPOTest() {
        driver.findElement(By.linkText("Form Authentication")).click();
        PageObject pageObject = new PageObject(driver);
        pageObject.login("tomsmith", "SuperSecretPassword!");

    }
}
