package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MyCarsScreen extends BaseScreen {
    public MyCarsScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.telran.ilcarro:id/addCarBtn")
    WebElement btnAddCar;

    @AndroidFindBy(xpath = "//android.widget.Toast[@text='Car was added!']")
    WebElement messageCarWasAdded;

    @AndroidFindBy(id = "android:id/button1")
    WebElement btnConfirmDelete;

    @AndroidFindBy(id = "com.telran.ilcarro:id/myCarSerialTxt")
    List<WebElement> carSerialNumberList;

    public void tapBtnAddCar() {
        btnAddCar.click();
    }

    public boolean checkTextInMessageCarWasAdded(String text) {
        return isTextInElementPresent(messageCarWasAdded, text);
    }

    public void deleteCar() {
        pause(2000);
        swipe(width/10, height/40*9, width/10*9, height/40*9);
    }

    public void tapBtnConfirmDelete() {
        clickWait(btnConfirmDelete);
    }

    public List<String> readTextInCarSerialNumberList() {
        List<String> list = new ArrayList<>();
        for (WebElement e : carSerialNumberList) {
            list.add(e.getText());
        }
        return list;
    }
}
