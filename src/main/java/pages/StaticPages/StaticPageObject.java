package pages.staticPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StaticPageObject {
    public static final By USERNAME = By.id("username");
    public static final By PASSWORD = By.id("password");
    public static final By LOGIN_BUTTON = By.id("login");

    public static void login(WebDriver webDriver, String username, String password) {
        webDriver.findElement(USERNAME).sendKeys(username);
        webDriver.findElement(PASSWORD).sendKeys(password);
        webDriver.findElement(LOGIN_BUTTON).click();
    }

}
