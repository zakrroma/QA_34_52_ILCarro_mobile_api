package mobile_tests;

import dto.UserDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

import static utils.PropertiesReader.*;

public class LoginTests extends TestBase {
    SearchScreen searchScreen;
    LoginScreen loginScreen;
    ErrorScreen errorScreen;
    UserDto user;

    @BeforeMethod
    public void goToLoginScreen() {
        new SplashScreen(driver).goToSearchScreen();

        searchScreen = new SearchScreen(driver);
        searchScreen.tapBtnDots();
        searchScreen.tapBtnLogin();

        loginScreen = new LoginScreen(driver);

        user = UserDto.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
    }

    @Test
    public void loginPositiveTest() {
        loginScreen.fillLoginForm(user);
        loginScreen.tapBtnSubmit();

        Assert.assertTrue(searchScreen
                .checkTextInMessageLoginSuccess("Login success!"));
    }
}
