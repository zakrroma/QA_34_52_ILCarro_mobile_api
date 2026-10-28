package controllers;

import dto.CarDto;
import dto.CarsDto;
import dto.TokenDto;
import dto.UserDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import utils.BaseApi;

import static io.restassured.RestAssured.given;
import static utils.PropertiesReader.getProperty;

public class CarController implements BaseApi {

    public TokenDto tokenDto;

    @BeforeClass
    public void login() {
        UserDto user = UserDto.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();

        tokenDto = given()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + LOGIN_URL)
                .thenReturn()
                .getBody()
                .as(TokenDto.class);
    }

    public Response addNewCar(CarDto car) {
        return given()
                .body(car)
                .contentType(ContentType.JSON)
                .header("Authorization", tokenDto.getAccessToken())
                .when()
                .post(BASE_URL + ADD_CAR_URL)
                .thenReturn();
    }

    public int getCarsCount() {
        CarsDto cars = given()
                .contentType(ContentType.JSON)
                .header("Authorization", tokenDto.getAccessToken())
                .when()
                .get(BASE_URL + GET_CARS_URL)
                .then()
                .statusCode(200)
                .extract().as(CarsDto.class);
        return cars.getCars().length;
    }
}
