package screens;

import dto.UserDto;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginScreen extends BaseScreen {
    public LoginScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.telran.ilcarro:id/editLoginEmail")
    WebElement inputEmail;

    @AndroidFindBy(id = "com.telran.ilcarro:id/editLoginPassword")
    WebElement inputPassword;

    @AndroidFindBy(id = "com.telran.ilcarro:id/loginBtn")
    WebElement btnSubmit;

    public void fillLoginForm(UserDto user) {
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    public void tapBtnSubmit() {
        btnSubmit.click();
    }
}
