package utils;

import dto.CarsDto;
import dto.TokenDto;
import dto.UserDto;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static utils.PropertiesReader.getProperty;

public interface IGetAllCars extends BaseApi {
    default int getCarsCount() {
        UserDto user = UserDto.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();

        TokenDto tokenDto = given()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + LOGIN_URL)
                .thenReturn()
                .getBody()
                .as(TokenDto.class);

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
