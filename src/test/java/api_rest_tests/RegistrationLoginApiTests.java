package api_rest_tests;

import controllers.AuthorizationController;
import dto.UserDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static utils.PropertiesReader.getProperty;
import static utils.UserFactory.*;

public class RegistrationLoginApiTests extends AuthorizationController {
    @Test
    public void registrationPositiveApiTest() {
        UserDto user = positiveUser();

        Assert.assertEquals(registrationLogin(user, REG_URL)
                .getStatusCode(),200);
    }

    @Test
    public void loginPositiveApiTest() {
        UserDto user = UserDto.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();

        Response response = registrationLogin(user, LOGIN_URL);
        //System.out.println(response.getBody().asString());

        //TokenDto tokenDto = response.getBody().as(TokenDto.class);
        //System.out.println(tokenDto.getAccessToken());

        Assert.assertEquals(response.getStatusCode(),200);
    }
}
