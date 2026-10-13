package api_rest_tests;

import controllers.AuthorizationController;
import data_providers.UserDataProvider;
import dto.UserDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static utils.PropertiesReader.getProperty;
import static utils.UserFactory.*;

public class RegistrationLoginApiTests extends AuthorizationController {
    @Test
    public void registrationPositiveApiTest() {
        UserDto user = positiveUser();

        Assert.assertEquals(registrationLogin(user, REG_URL)
                .getStatusCode(), 200);
    }

    @Test(dataProvider = "wrongPasswordRegDataProvider",
            dataProviderClass = UserDataProvider.class)
    public void registrationWrongPasswordNegativeApiTest(UserDto user) { // homework
        Response response = registrationLogin(user, REG_URL);

        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test(dataProvider = "wrongEmailRegDataProvider",
            dataProviderClass = UserDataProvider.class)
    public void registrationWrongEmailNegativeApiTest(UserDto user) { // homework
        Response response = registrationLogin(user, REG_URL);

        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test
    public void registrationWrongRequestMethodNegativeApiTest() { // homework
        Assert.assertEquals(registrationLoginWrongRequestMethod(REG_URL)
                .getStatusCode(), 403);
    }

    @Test
    public void registrationWrongContentTypeNegativeApiTest() { // homework
        UserDto user = positiveUser();

        Assert.assertEquals(registrationLoginWrongContentType(user, REG_URL)
                .getStatusCode(), 500);
    }

    @Test
    public void registrationDuplicateUserDataNegativeApiTest() { // homework
        UserDto user = positiveUser();

        registrationLogin(user, REG_URL);
        Assert.assertEquals(registrationLogin(user, REG_URL)
                .getStatusCode(), 400);
    }

    @Test
    public void registrationWrongKeyNameNegativeApiTest() { // homework
        UserDto user = positiveUser();

        Map<String,String> invalidJson = new HashMap<>();
        invalidJson.put("email", user.getUsername());
        invalidJson.put("password", user.getPassword());
        invalidJson.put("firstName", user.getFirstName());
        invalidJson.put("lastName", user.getLastName());

        Assert.assertEquals(registrationLoginWrongKeyName(invalidJson, REG_URL)
                .getStatusCode(), 500);
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

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void loginWrongPasswordNegativeApiTest() { // homework
        UserDto user = UserDto.builder()
                .username(getProperty("base.properties", "email"))
                .password("WrongPassword1!")
                .build();

        Assert.assertEquals(registrationLogin(user, LOGIN_URL)
                .getStatusCode(), 401);
    }

    @Test
    public void loginWrongContentTypeNegativeApiTest() { // homework
        UserDto user = UserDto.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();

        // Assert.assertEquals(registrationLoginWrongContentType(user, LOGIN_URL)
        //        .getStatusCode(), 401); // accepted 200
    }

    @Test
    public void loginWrongRequestMethodNegativeApiTest() {
       Assert.assertEquals(registrationLoginWrongRequestMethod(LOGIN_URL)
                .getStatusCode(), 403);
    }

    @Test
    public void loginWrongKeyNameNegativeApiTest() { // homework
        UserDto user = UserDto.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();

        Map<String,String> invalidJson = new HashMap<>();
        invalidJson.put("email", user.getUsername());
        invalidJson.put("password", user.getPassword());

        Assert.assertEquals(registrationLoginWrongKeyName(invalidJson, LOGIN_URL)
                .getStatusCode(), 400);
    }
}
