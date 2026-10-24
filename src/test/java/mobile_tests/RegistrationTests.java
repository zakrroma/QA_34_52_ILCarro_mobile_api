package mobile_tests;

import dto.UserDto;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.RegistrationScreen;
import screens.SearchScreen;
import screens.SplashScreen;

import static utils.UserFactory.*;

public class RegistrationTests extends TestBase{
    @Test
    public void registrationPositiveTest() {
        new SplashScreen(driver).goToSearchScreen();

        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.tapBtnDots();
        searchScreen.tapBtnRegistration();

        UserDto userDto = positiveUser();

        RegistrationScreen registrationScreen = new RegistrationScreen(driver);
        registrationScreen.fillRegForm(userDto);
        registrationScreen.tapCheckboxAgree();
        registrationScreen.tapSubmit();

        Assert.assertTrue(searchScreen.checkMessageRegistrationSuccess());
    }
}
