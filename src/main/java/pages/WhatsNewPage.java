package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WhatsNewPage extends BasePage{
    private final By whatsNewText = By.xpath("//h1[contains(text(), \"What's New at Krisp\")]");
    private final By latestFeatures = By.xpath("//p[text() = 'Check the latest feature releases and see what’s new at Krisp']");


    public WhatsNewPage(WebDriver driver) {
        super(driver);
    }

    public void redirectToWhatsNewPage(){
        utils.switchToNewWindow();
    }

    public String getWhatsNewPageURL(){
        return utils.getCurrentPageUrl();
    }

    public String getWhatsNewText(){
        return utils.getText(whatsNewText);
    }

    public String getLatestFeaturesText(){
        return utils.getText(latestFeatures);
    }
}
