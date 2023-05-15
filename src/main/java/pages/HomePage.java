package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{
    private final By userDropdown = By.cssSelector("div.user-dropdown");
    private final By userEmail = By.cssSelector("p[data-test-id='userEmail']");
    private final By helpDropdown = By.cssSelector("div.help-dropdown");
    private final By whatsNew = By.xpath("//a[text()='What’s new' and @href='https://whatsnew.krisp.ai/']");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickUserDropdown(){
       utils.waitForElementToBeClickable(userDropdown,20);
       utils.click(userDropdown);
       return this;
    }

    public String getUserEmail(){
       return utils.getText(userEmail);
    }

    public HomePage clickHelpDropdown(){
        utils.click(helpDropdown);
        return this;
    }

    public WhatsNewPage clickWhatsNew(){
        utils.waitForElementToBeClickable(whatsNew,20);
        utils.click(whatsNew);
        return new WhatsNewPage(driver);
    }
}
