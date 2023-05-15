import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.DriverUtils;
import utils.Mailinator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.WhatsNewPage;

public class KrispTest {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    WhatsNewPage whatsNewPage;

    @BeforeClass
    public void setUp(){
        driver = DriverUtils.getDriver("chrome");
        driver.get("https://account.krisp.ai/login");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @Test
    public void krispTest(){
        var email = "krisp_test1@mailinator.com";
        var expectedWhatsNewURL = "https://whatsnew.krisp.ai/";
        var expectedWhatsNewText = "What's New at Krisp";
        var expectedLatestFeaturesText = "Check the latest feature releases and see what’s new at Krisp";
        loginPage.enterEmail(email)
                .clickSignIn();
        //on this step the user should pass the recaptcha manually
        loginPage.clickSignIn();

        //Mailinator is an opensource test email system, from which we'll get the code
        var code = Mailinator.getLastReceivedCode(email);

        loginPage.enterVerificationCode(code);
        homePage.clickUserDropdown();


        Assert.assertEquals(homePage.getUserEmail(),email);
        homePage.clickHelpDropdown();
        whatsNewPage = homePage.clickWhatsNew();
        whatsNewPage.redirectToWhatsNewPage();
        Assert.assertEquals(whatsNewPage.getWhatsNewPageURL(),expectedWhatsNewURL);
        Assert.assertEquals(whatsNewPage.getWhatsNewText(),expectedWhatsNewText);
        Assert.assertEquals(whatsNewPage.getLatestFeaturesText(),expectedLatestFeaturesText);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
