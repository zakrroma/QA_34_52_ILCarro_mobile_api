package api_rest_tests;

import controllers.CarController;
import dto.CarDto;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CarFactory;

public class CarApiTests extends CarController {
    @Test
    public void addCarPositiveTest() {
        CarDto carDto = CarFactory.positiveCar();

        Assert.assertEquals(addNewCar(carDto).getStatusCode(), 200);
    }
}
