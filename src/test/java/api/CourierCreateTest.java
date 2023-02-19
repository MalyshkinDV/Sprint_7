package api;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourierCreateTest {
    private CourierApi courierApi;
    private String login;
    private String password;
    private String firstName;
    private int id;

    @Test
    @DisplayName("Create a new courier")
    @Description("Создание нового курьера")
    public void checkNewCourierCreate() {
        Response response = courierApi.courierCreate(login, password, firstName);
        courierApi.successCourierCreated(response);
    }

    @Test
    @DisplayName("Create a duplicate courier")
    @Description("Создание существующего в системе курьера")
    public void checkDuplicateCourierCreate() {
        courierApi.courierCreate(login, password, firstName);
        Response response = courierApi.courierCreate(login, password, firstName);
        courierApi.duplicateCourierCreated(response);
    }

    @Test
    @DisplayName("Create a courier without key \"login\"")
    @Description("Создание курьера без ключа \"login\"")
    public void checkWithoutLoginCourierCreate() {
        Response response = courierApi.courierCreateWithoutLogin(password, firstName);
        courierApi.withoutLoginCourierCreated(response);
    }

    @Test
    @DisplayName("Create a courier without key \"password\"")
    @Description("Создание курьера без ключа \"password\"")
    public void checkWithoutPasswordCourierCreate() {
        Response response = courierApi.courierCreateWithoutPassword(login, firstName);
        courierApi.withoutPasswordCourierCreated(response);
    }

    @Test
    @DisplayName("Create a courier with empty value of key \"login\"")
    @Description("Создание курьера с пустым значением ключа \"login\"")
    public void checkEmptyLoginCourierCreate() {
        Response response = courierApi.courierCreateEmptyLogin(login, password, firstName);
        courierApi.emptyLoginCourierCreated(response);
    }
    @Test
    @DisplayName("Create a courier with empty value of key \"password\"")
    @Description("Создание курьера с пустым значением ключа \"password\"")
    public void checkEmptyPasswordCourierAdd() {
        Response response = courierApi.courierCreateEmptyPassword(login, password, firstName);
        courierApi.emptyPasswordCourierCreated(response);
    }

    @Before
    public void setUp() {
        courierApi = new CourierApi();
        login = RandomStringUtils.randomAlphanumeric(4,8);
        password = RandomStringUtils.randomAlphanumeric(4,8);
        firstName = RandomStringUtils.randomAlphabetic(4,8);
    }

    @After
    public void tearDown() {
        try {
            id = courierApi.getCourierId(login, password);
        } catch (Exception e) {
        }
        courierApi.deleteCourierById(id);
    }
}