package pages;

import org.openqa.selenium.WebDriver;
import utils.SeleniumUtils;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final SeleniumUtils utils;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        utils = new SeleniumUtils(driver);
    }
}
