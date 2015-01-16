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

//    public By get(String elementName){
//        String[] values = locators.getProperty(elementName).split("=", 1);
//        switch (values[0]) {
//
//        }
//        return
//    }
}
