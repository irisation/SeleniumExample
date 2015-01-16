package base;

import org.openqa.selenium.WebDriver;

/**
 * Created by Admin on 14.01.2015.
 */
public class PageBase {
    protected final WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }
}
