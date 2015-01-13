package interactions;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 09.01.2015.
 */
public class TabsTest {
    private static final String BASE_SITE = "http://www.w3schools.com/html/html5_draganddrop.asp";
    private WebDriver driver;


    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
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
    public void tabsTest() throws InterruptedException {
        WebElement tryItButton = driver.findElement(By.cssSelector(".tryitbtn"));
        Assert.assertEquals(driver.getWindowHandles().size(), 1);
        tryItButton.click();
        Assert.assertEquals(driver.getWindowHandles().size(), 2);
        ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(handles.get(0));
        Assert.assertEquals(driver.getTitle(), "HTML5 Drag and Drop");
        driver.switchTo().window(handles.get(1));
        Assert.assertEquals(driver.getTitle(), "Tryit Editor v2.3");
        driver.close();
    }
}

