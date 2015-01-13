package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageObject {
    private static final By USERNAME = By.id("username");
    private static final By PASSWORD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login");

    private WebDriver driver;

    public PageObject(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getUsername() {
        return driver.findElement(USERNAME);
    }

    public WebElement getPassword() {
        return driver.findElement(PASSWORD);
    }

    public WebElement getLoginButton() {
        return driver.findElement(LOGIN_BUTTON);
    }
    public void login(String user, String pass) {
        getUsername().sendKeys(user);
        getPassword().sendKeys(pass);
        getLoginButton().click();
    }

}
