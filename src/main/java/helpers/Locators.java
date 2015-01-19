package helpers;


import org.openqa.selenium.By;

import java.io.InputStream;
import java.util.Properties;

public class Locators {
    private static final Properties locators;
    private enum LocatorType{
        id, name, css, xpath, tag, text, partText
    }

    static {
        locators = new Properties();
        InputStream is = Locators.class.getResourceAsStream("/locators.properties");
        try{
            locators.load(is);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static String title(String pageName) {
        return locators.getProperty(pageName);
    }

    public static By get(String locatorName) {
        String propertyValue = locators.getProperty(locatorName);
        return getLocator(propertyValue);
    }

    public static By get(String locatorName, String parameter) {
        String propertyValue = locators.getProperty(locatorName);
        return getLocator(String.format(propertyValue, parameter));
    }

    public static By getLocator(String elementName) {
        String[] values = locators.getProperty(elementName).split("=", 1);
        LocatorType locatorType = LocatorType.valueOf(values[0]);
        switch (locatorType) {
            case id: {
                return By.id(values[1]);
            }
            case name: {
                return By.name(values[1]);
            }
            case css: {
                return By.cssSelector(values[1]);
            }
            case xpath: {
                return By.xpath(values[1]);
            }
            case tag: {
                return By.tagName(values[1]);
            }
            case text: {
                return By.linkText(values[1]);
            }
            case partText: {
                return By.partialLinkText(values[1]);
            }
            default:
                throw new IllegalArgumentException("No such locator type: " + values[0]);
        }

    }


}
