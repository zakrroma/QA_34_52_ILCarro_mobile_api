package screens;

import dto.CarDto;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddCarScreen extends BaseScreen {
    public AddCarScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.telran.ilcarro:id/imageCar")
    WebElement linkImageCar;

    @AndroidFindBy(id = "com.telran.ilcarro:id/editSerial")
    WebElement inputSerialNumber;

    @AndroidFindBy(id = "com.telran.ilcarro:id/editMan")
    WebElement inputManufacture;

    @AndroidFindBy(id = "com.telran.ilcarro:id/editModel")
    WebElement inputModel;

    @AndroidFindBy(id = "com.telran.ilcarro:id/editCity")
    WebElement inputCity;

    @AndroidFindBy(id = "com.telran.ilcarro:id/editPrice")
    WebElement inputPrice;

    @AndroidFindBy(id = "com.telran.ilcarro:id/editCarClass")
    WebElement inputCarClass;

    @AndroidFindBy(id = "com.telran.ilcarro:id/text1")
    WebElement inputFuelType;

    @AndroidFindBy(id = "com.telran.ilcarro:id/editYear")
    WebElement inputYear;

    @AndroidFindBy(id = "com.telran.ilcarro:id/editSeats")
    WebElement inputSeats;

    @AndroidFindBy(id = "com.telran.ilcarro:id/editAbout")
    WebElement inputAbout;

    @AndroidFindBy(id = "com.telran.ilcarro:id/addCarBtn")
    WebElement btnSubmit;

    public void fillAddCarForm(CarDto car) {
        inputSerialNumber.sendKeys(car.getSerialNumber());
        inputManufacture.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputCity.sendKeys(car.getCity());
        inputPrice.sendKeys(car.getPricePerDay()+"");
        inputCarClass.sendKeys(car.getCarClass());
        chooseFuelType(car.getFuel());
        inputYear.sendKeys(car.getYear());
        inputSeats.sendKeys(car.getSeats()+"");
        inputAbout.sendKeys(car.getAbout());
    }

    public void tapBtnSubmit() {
        btnSubmit.click();
    }

    public void chooseFuelType(String fuelType) {
        inputFuelType.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(
                        "//*[@text='" + fuelType + "']"))).click();
    }
}
