package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{
    private final By emailInput = By.cssSelector("input[data-test-id='signInEmailField']");
    private final By signInButton = By.cssSelector("button[data-test-id='signInButton']");
    private final By verificationCodeInput = By.cssSelector("div.verify-box");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage enterEmail(String email){
        utils.sendKeys(emailInput,email);
        return this;
    }

    public LoginPage clickSignIn(){
        utils.waitForElementToBeClickable(signInButton,30);
        utils.click(signInButton);
        return this;
    }

    public LoginPage enterVerificationCode(String code){
        utils.waitForElement(verificationCodeInput,20);
        var parentDiv = utils.findElement(verificationCodeInput);

        var inputElements = parentDiv.findElements(By.cssSelector("input[type='text']")).toArray(new WebElement[0]);

        for (int i = 0; i < code.length(); i++) {
            inputElements[i].sendKeys(String.valueOf(code.charAt(i)));
        }

        return this;
    }
}
