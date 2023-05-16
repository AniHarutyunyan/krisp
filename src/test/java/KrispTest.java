import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.WhatsNewPage;
import utils.DriverUtils;
import utils.Mailinator;

public class KrispTest {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    WhatsNewPage whatsNewPage;

    @BeforeClass
    public void setUp() {
        driver = DriverUtils.getDriver("chrome");
        driver.get("https://account.krisp.ai/login");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @Test
    public void krispTest() throws InterruptedException {
        var email = "krisp_test1@mailinator.com";
        var expectedWhatsNewURL = "https://whatsnew.krisp.ai/";
        var expectedWhatsNewText = "What's New at Krisp";
        var expectedLatestFeaturesText = "Check the latest feature releases and see what’s new at Krisp";
        var expectedLoginURL = "https://account.krisp.ai/login";
        var expectedLoginPageTitle = "Login";
        loginPage.enterEmail(email)
                 .clickSignIn()
                 .clickSignIn(); //on this step the user should pass the recaptcha manually and click signin again

        //Mailinator is an opensource test email system, from where we'll get the code
        var code = Mailinator.getLastReceivedCode(email);

        loginPage.enterVerificationCode(code);
        homePage.clickUserDropdown();

        Assert.assertEquals(homePage.getUserEmail(), email);

        homePage.clickHelpDropdown();
        whatsNewPage = homePage.clickWhatsNew();
        whatsNewPage.redirectToWhatsNewPage();
        Assert.assertEquals(whatsNewPage.getWhatsNewPageURL(), expectedWhatsNewURL);
        Assert.assertEquals(whatsNewPage.getWhatsNewText(), expectedWhatsNewText);
        Assert.assertEquals(whatsNewPage.getLatestFeaturesText(), expectedLatestFeaturesText);

        homePage.navigateToHomePage()
                .clickUserDropdown()
                .clickSignOut();

        Assert.assertEquals(loginPage.getLoginPageURL(), expectedLoginURL);
        Assert.assertEquals(loginPage.getLoginPageTitle(), expectedLoginPageTitle);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
