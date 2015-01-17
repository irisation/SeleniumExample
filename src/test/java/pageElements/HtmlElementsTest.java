package pageElements;

import base.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.pageElements.SearchPage;

import java.util.concurrent.TimeUnit;


public class HtmlElementsTest extends TestBase {

    private SearchPage searchPage;

    @Test
    public void searchTest() throws InterruptedException {
        searchPage = new SearchPage(driver);
        searchPage.search("yandex");
        Thread.sleep(3000);
    }

    @Test
    public void authorizationTest() throws InterruptedException {
        searchPage = new SearchPage(driver);
        searchPage.login("yadad", "ADFWFF");
        Thread.sleep(3000);
    }
}
