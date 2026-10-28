package mobile_tests;

import dto.CarDto;
import dto.UserDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;
import utils.CarFactory;
import utils.IGetAllCars;

import java.util.List;

import static utils.PropertiesReader.getProperty;

public class DeleteCarTest extends TestBase implements IGetAllCars {
    MyCarsScreen myCarsScreen;

    @BeforeMethod
    public void addCarToDelete() {
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

        AddCarScreen addCarScreen = new AddCarScreen(driver);

        CarDto car = CarFactory.positiveCar();

        addCarScreen.fillAddCarForm(car);
        addCarScreen.tapBtnSubmit();
    }

    @Test
    public void deleteCarPositiveTest() {
        List<String> listBeforeDelete = myCarsScreen.readTextInCarSerialNumberList();

        myCarsScreen.deleteCar();
        myCarsScreen.tapBtnConfirmDelete();

        List<String> listAfterDelete = myCarsScreen.readTextInCarSerialNumberList();

        Assert.assertNotEquals(listBeforeDelete, listAfterDelete);
    }

    @Test
    public void deleteCarPositiveTestUsingApi() {
        int countBeforeDelete = getCarsCount();
        System.out.println("Count before delete: " + countBeforeDelete);

        myCarsScreen.deleteCar();
        myCarsScreen.tapBtnConfirmDelete();

        int countAfterDelete = getCarsCount();
        System.out.println("Count after delete: " + countAfterDelete);

        Assert.assertNotEquals(countAfterDelete, countBeforeDelete);
    }
}
