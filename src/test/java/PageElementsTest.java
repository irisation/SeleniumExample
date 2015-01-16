import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageElements.SearchArrow;
import pages.ElementsPage;
import pages.SearchPage;

import java.util.concurrent.TimeUnit;


public class PageElementsTest {
    private static final String BASE_SITE = "http://yandex.by";
    private WebDriver driver;
    private SearchPage searchPage;


    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(4, TimeUnit.SECONDS);
        driver.get(BASE_SITE);
        searchPage = new SearchPage(driver);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void findTest() throws InterruptedException {
        searchPage.search("google");
        Thread.sleep(3000);


//        ElementsPage elementsPage = new ElementsPage(driver);
//        elementsPage.find("google");
    }
}
