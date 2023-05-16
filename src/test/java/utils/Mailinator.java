package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Mailinator {
    private static WebDriver driver;

    public static String getLastReceivedCode(String email) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://www.mailinator.com/v4/public/inboxes.jsp?msgid=krisp_test-1683896969-14735387&to=" + email.substring(0, email.indexOf("@")));

        Thread.sleep(10);
        WebElement codeElement = driver.findElement(By.xpath("//td[contains(text(), 'Krisp verification code:')]"));
        String codeText = codeElement.getText().trim();
        String code = codeText.replace("Krisp verification code:", "").trim();
        driverQuit();

        return code;
    }

    private static void driverQuit() {
        driver.quit();
    }
}
