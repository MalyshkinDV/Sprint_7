package api;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourierLoginTest {
    private CourierApi courierApi;
    private String login;
    private String password;
    private String firstName;

    @Test
    @DisplayName("Authorization successful")
    @Description("Успешная авторизация")
    public void checkAuthCourierSuccess(){
        Response response = courierApi.authCourier(login, password);
        courierApi.authCourierSuccess(response);
    }

    @Test
    @DisplayName("Authorization without key \"login\"")
    @Description("Авторизация без ключа \"login\"")
    public void checkAuthCourierWithoutLogin() {
        Response response = courierApi.authCourierWithoutLogin(password);
        courierApi.withoutLoginCourierAuthorized(response);
    }

    @Test
    @DisplayName("Authorization without key \"password\"")
    @Description("Авторизация без ключа \"password\"")
    public void checkAuthCourierWithoutPassword() {
        Response response = courierApi.authCourierWithoutPassword(login);
        courierApi.withoutPasswordCourierAuthorized(response);
    }

    @Test
    @DisplayName("Authorization with empty value of key \"login\"")
    @Description("Авторизация c пустым значением ключа \"login\"")
    public void checkAuthCourierEmptyLogin() {
        Response response = courierApi.authCourierEmptyLogin(login, password);
        courierApi.emptyLoginCourierAuthorized(response);
    }

    @Test
    @DisplayName("Authorization with empty value of key \"password\"")
    @Description("Авторизация c пустым значением ключа \"password\"")
    public void checkAuthCourierEmptyPassword() {
        Response response = courierApi.authCourierEmptyPassword(login, password);
        courierApi.emptyPasswordCourierAuthorized(response);
    }

    @Test
    @DisplayName("Authorization with incorrect value of key \"login\"")
    @Description("Авторизация c некорректным значением ключа \"login\"")
    public void checkAuthCourierIncorrectLogin() {
        Response response = courierApi.authCourierLoginIncorrect(login, password);
        courierApi.incorrectLoginCourierAuthorized(response);
    }

    @Test
    @DisplayName("Authorization with incorrect value of key \"password\"")
    @Description("Авторизация c некорректным значением ключа \"password\"")
    public void checkAuthCourierIncorrectPassword() {
        Response response = courierApi.authCourierPasswordIncorrect(login, password);
        courierApi.incorrectPasswordCourierAuthorized(response);
    }

    @Before
    public void setUp() {
        courierApi = new CourierApi();
        login = RandomStringUtils.randomAlphanumeric(4,8);
        password = RandomStringUtils.randomAlphanumeric(4,8);
        firstName = RandomStringUtils.randomAlphabetic(4,8);
        courierApi.courierCreate(login, password, firstName);
    }

    @After
    public void tearDown() {
        int id = courierApi.getCourierId(login, password);
        courierApi.deleteCourierById(id);
    }
}

