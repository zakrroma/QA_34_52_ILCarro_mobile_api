package controllers;

import dto.UserDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.BaseApi;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthorizationController implements BaseApi {
    public Response registrationLogin(UserDto user, String url) {
        return given()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + url)
                .thenReturn();
    }

    public Response registrationLoginWrongContentType(UserDto user, String url) {
        return given()
                .body(user)
                .contentType(ContentType.HTML)
                .when()
                .post(BASE_URL + url)
                .thenReturn();
    }

    public Response registrationLoginWrongRequestMethod(String url) {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(BASE_URL + url)
                .thenReturn();
    }

    public Response registrationLoginWrongKeyName(Map<String,String> invalidData, String url) {
        return given()
                .body(invalidData)
                .contentType(ContentType.HTML)
                .when()
                .post(BASE_URL + url)
                .thenReturn();
    }
}
