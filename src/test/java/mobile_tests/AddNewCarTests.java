package mobile_tests;

import dto.CarDto;
import dto.UserDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;
import utils.CarFactory;

import static utils.PropertiesReader.getProperty;

public class AddNewCarTests extends TestBase {
    AddCarScreen addCarScreen;
    MyCarsScreen myCarsScreen;
    CarDto car;

    @BeforeMethod
    public void goToAddCarScreen() {
        new SplashScreen(driver).goToSearchScreen();

        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.tapBtnDots();
        searchScreen.tapBtnLogin();

        LoginScreen loginScreen = new LoginScreen(driver);

        UserDto user = UserDto.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();

        loginScreen.fillLoginForm(user);
        loginScreen.tapBtnSubmit();

        searchScreen.tapBtnDots();
        searchScreen.tapBtnMyCars();

        myCarsScreen = new MyCarsScreen(driver);
        myCarsScreen.tapBtnAddCar();

        addCarScreen = new AddCarScreen(driver);

        car = CarFactory.positiveCar();
    }

    @Test
    public void addCarPositiveTest() {
        addCarScreen.fillAddCarForm(car);
        addCarScreen.tapBtnSubmit();

        Assert.assertTrue(myCarsScreen.checkTextInMessageCarWasAdded("Car was added!"));
    }
}
