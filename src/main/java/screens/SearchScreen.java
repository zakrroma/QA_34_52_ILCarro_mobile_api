package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchScreen extends BaseScreen {
    public SearchScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
    WebElement btnDots;

    @AndroidFindBy(xpath = "//*[@resource-id='com.telran.ilcarro:id/title'" +
            "and @text='Login']")
    WebElement btnLogin;

    @AndroidFindBy(xpath = "//*[@resource-id='com.telran.ilcarro:id/title'" +
            "and @text='Registration']")
    WebElement btnRegistration;

    @AndroidFindBy(xpath = "//*[@resource-id='com.telran.ilcarro:id/title'" +
            " and @text='My Cars']")
    WebElement btnMyCars;

    @AndroidFindBy(xpath = "//*[@resource-id='com.telran.ilcarro:id/title'" +
            " and @text='Logout']")
    WebElement btnLogout;

    @AndroidFindBy(xpath = "//android.widget.Toast[@text='Registration success!']")
    WebElement messageRegistrationSuccess;

    @AndroidFindBy(xpath = "//android.widget.Toast[@text='Login success!']")
    WebElement messageLoginSuccess;


    public void tapBtnDots() {
        clickWait(btnDots);
    }

    public void tapBtnLogin() {
        clickWait(btnLogin);
    }

    public void tapBtnRegistration() {
        clickWait(btnRegistration);
    }

    public void tapBtnMyCars() {
        clickWait(btnMyCars);
    }

    public void tapBtnLogout() {
        clickWait(btnLogout);
    }

    public boolean checkTextInMessageRegistrationSuccess(String text) {
        return isTextInElementPresent(messageRegistrationSuccess, text);
    }

    public boolean checkTextInMessageLoginSuccess(String text) {
        return isTextInElementPresent(messageLoginSuccess, text);
    }

    /*
    public boolean checkMessagePresence() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//android.widget" +
                                    ".Toast[@text='Registration success!']")));
            return true;
        } catch (TimeoutException e) {
            throw new TimeoutException();
        }
    }
    */
}
