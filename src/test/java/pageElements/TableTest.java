package pageElements;


import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class TableTest {
    private static final String BASE_SITE = "http://the-internet.herokuapp.com/challenging_dom";
    private WebDriver driver;
    By locator = By.cssSelector("div .large-10 table");
    Table table;


    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(4, TimeUnit.SECONDS);
        driver.get(BASE_SITE);
        table = new Table(driver, locator);

    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void getHeaderTest() {
        String[] check = new String[]{"Lorem", "Ipsum", "Dolor", "Sit", "Amet", "Diceret", "Action"};
        Assert.assertEquals(Arrays.asList(check), table.getHeaderAsString());
    }

    @Test
    public void getRowsTest() {
        System.out.println(table.getRowsAsString());

    }

    @Test
    public void getColumnsTest() {
        System.out.println(table.getColumnsAsString());
    }

    @Test
    public void getColumnTest() {
        table.getColumn(3).get(0).getText();
    }

    @Test
    public void getRowTest() {
        table.getRow(3).get(0).getText();
    }

    @Test
    public void getCellTest() {
        table.getCell(4, 4).getText();
    }

}
