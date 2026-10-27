package mobile_tests;

import data_providers.UserDataProvider;
import dto.UserDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ErrorScreen;
import screens.RegistrationScreen;
import screens.SearchScreen;
import screens.SplashScreen;

import static utils.PropertiesReader.getProperty;
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
    public void registrationEmptyLastNameFieldNegativeTest() {
        user.setLastName("");

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

    @Test
    public void registrationDuplicateUserNegativeTest() {
        user.setUsername(getProperty("base.properties", "email"));
        user.setPassword(getProperty("base.properties", "password"));

        registrationScreen.fillRegistrationForm(user);
        registrationScreen.tapCheckboxAgree();
        registrationScreen.tapBtnSubmit();

        errorScreen =  new ErrorScreen(driver);

        Assert.assertTrue(errorScreen
                .checkTextInMessage("User already exists"));
    }

    @Test(dataProvider = "wrongEmailRegDataProvider",
            dataProviderClass = UserDataProvider.class)
    public void registrationIncorrectEmailNegativeTest(UserDto wrongUser) {
        registrationScreen.fillRegistrationForm(wrongUser);
        registrationScreen.tapCheckboxAgree();
        registrationScreen.tapBtnSubmit();

        errorScreen =  new ErrorScreen(driver);

        Assert.assertTrue(errorScreen
                .checkTextInMessage("{username=must be a well-formed email address}"));
    }

    @Test(dataProvider = "wrongPasswordRegDataProvider",
            dataProviderClass = UserDataProvider.class)
    public void registrationIncorrectPasswordNegativeTest(UserDto wrongUser) {
        registrationScreen.fillRegistrationForm(wrongUser);
        registrationScreen.tapCheckboxAgree();
        registrationScreen.tapBtnSubmit();

        errorScreen =  new ErrorScreen(driver);

        Assert.assertTrue(errorScreen
                .checkTextInMessage("{password= At least 8 characters; " +
                        "Must contain at least 1 uppercase letter, " +
                        "1 lowercase letter, and 1 number; " +
                        "Can contain special characters [@$#^&*!]}"));
    }
}
