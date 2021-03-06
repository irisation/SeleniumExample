import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ElementsPage;


import java.util.concurrent.TimeUnit;

public class PageElementsTest {
    private static final String BASE_URL = "http://www.yandex.by";
    private WebDriver driver;
    private ElementsPage searchPage;


    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(4, TimeUnit.SECONDS);
        driver.get(BASE_URL);
        searchPage = new ElementsPage(driver);
    }

    @AfterMethod
    public void teardown() {
        driver.close();
    }

    @Test
    public void sampleTest() throws InterruptedException {
        searchPage.find("yandex");
        Thread.sleep(3000);
    }
}