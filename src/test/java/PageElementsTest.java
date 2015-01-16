import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ElementsPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 14.01.2015.
 */
public class PageElementsTest {
    private static final String BASE_SITE = "http://yandex.by";
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
    public void findTest() {
        ElementsPage elementsPage = new ElementsPage(driver);
        elementsPage.find("google");
    }
}
