package utils;

public interface BaseApi {
    // Authentication Controller
    String BASE_URL = "https://ilcarro-backend.herokuapp.com";
    String REG_URL = "/v1/user/registration/usernamepassword";
    String LOGIN_URL = "/v1/user/login/usernamepassword";

    // Car Controller
    String ADD_CAR_URL = "/v1/cars";
    String SEARCH_URL = "/v1/cars/search";
    String GET_CARS_URL = "/v1/cars/my";


}
