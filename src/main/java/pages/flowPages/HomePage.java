package pages.flowPages;

import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;

        // Check that we're on the right page.
        if (!"Home".equals(driver.getTitle())) {
            // Alternatively, we could navigate to the login page, perhaps logging out first
            throw new IllegalStateException("This is not the home page");
        }
    }

    public boolean verifyThatOnHomePage() {
        return true;
    }
}
