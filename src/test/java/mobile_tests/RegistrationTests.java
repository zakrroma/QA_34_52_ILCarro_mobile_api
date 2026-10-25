package mobile_tests;

import dto.UserDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ErrorScreen;
import screens.RegistrationScreen;
import screens.SearchScreen;
import screens.SplashScreen;

import static utils.UserFactory.*;

public class RegistrationTests extends TestBase {
    SearchScreen searchScreen;
    RegistrationScreen registrationScreen;
    ErrorScreen errorScreen;
    UserDto user;

    @BeforeMethod
    public void goToRegistrationScreen() {
        new SplashScreen(driver).goToSearchScreen();

        searchScreen = new SearchScreen(driver);
        searchScreen.tapBtnDots();
        searchScreen.tapBtnRegistration();

        registrationScreen = new RegistrationScreen(driver);

        user = positiveUser();
    }

    @Test
    public void registrationPositiveTest() {
        registrationScreen.fillRegistrationForm(user);
        registrationScreen.tapCheckboxAgree();
        registrationScreen.tapBtnSubmit();

        Assert.assertTrue(searchScreen
                .checkTextInMessageRegistrationSuccess("Registration success!"));
    }

    @Test
    public void registrationEmptyNameFieldNegativeTest() {
        user.setFirstName("");

        registrationScreen.fillRegistrationForm(user);
        registrationScreen.tapCheckboxAgree();
        registrationScreen.tapBtnSubmit();

        errorScreen =  new ErrorScreen(driver);
        Assert.assertTrue(errorScreen
                .checkTextInMessage("All fields must be filled and agree terms"));
    }

    @Test
    public void registrationNoCheckboxNegativeTest() {
        registrationScreen.fillRegistrationForm(user);
        registrationScreen.tapBtnSubmit();

        errorScreen =  new ErrorScreen(driver);
        Assert.assertTrue(errorScreen
                .checkTextInMessage("All fields must be filled and agree terms"));
    }
}
