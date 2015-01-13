package helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Waiter {

    public static void waitForTitle(WebDriver driver, String title) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.titleIs(title));
    }

    public static void waitForAlert(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void waitForAlert(WebDriver driver, final String title) {
        final WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(new ExpectedCondition<Alert>() {
            @Override
            public Alert apply(WebDriver webDriver) {
                Alert currAlert = wait.until(ExpectedConditions.alertIsPresent());
                if (currAlert.getText().contentEquals(title)) {
                    return currAlert;
                } else {
                    return null;
                }
            }
        });
    }

    public static void waitForHandleCountMoreThanOne(final WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return webDriver.getWindowHandles().size() > 1;
            }
        });
    }

    public static void waitForHandleCountMoreThan(WebDriver driver, final int handleCount){
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return webDriver.getWindowHandles().size() > handleCount;
            }
        });
    }

    public static WebElement waitForElementIsClickable(WebDriver driver, By locator){
        WebDriverWait wait = new WebDriverWait(driver, 60);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForElementsPresentMoreThan(WebDriver driver, final By locator, final int count){
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElements(locator).size() > count;
            }
        });
    }

    public static WebElement waitForTextInElementPresent(WebDriver driver, final By locator, final String text){
        final WebDriverWait wait = new WebDriverWait(driver, 60);
        return wait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                WebElement currElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                if( currElement.getText().contentEquals(text)) {
                    return currElement;
                } else {
                    return null;
                }
            }
        });
    }

    public static WebElement waitForElement(final WebDriver driver, final By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        return wait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return driver.findElement(locator);
            }
        });
    }

    public static boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
