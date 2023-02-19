package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.request.CourierModel;
import org.apache.commons.lang3.RandomStringUtils;

import static org.hamcrest.Matchers.*;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

public class CourierApi extends Specifications {
    private final String courierUri = "/api/v1/courier";
    private final String loginUri = "/api/v1/courier/login";

    public Response courierCreate(String login, String password, String firstName) {
        CourierModel courierModel = new CourierModel(login, password, firstName);
        return sendPostRequest(courierUri, courierModel);
    }

    public Response courierCreateEmptyLogin(String login, String password, String firstName) {
        login = "";
        CourierModel courierModel = new CourierModel(login, password, firstName);
        return sendPostRequest(courierUri, courierModel);
    }

    public Response courierCreateEmptyPassword(String login, String password, String firstName) {
        password = "";
        CourierModel courierModel = new CourierModel(login, password, firstName);
        return sendPostRequest(courierUri, courierModel);
    }

    public Response courierCreateWithoutLogin(String password, String firstName) {
        CourierModel courierModel = new CourierModel();
        courierModel.setPassword(password);
        courierModel.setFirstName(firstName);
        return sendPostRequest(courierUri, courierModel);
    }

    @Step("Check courier create without key \"password\"")
    public Response courierCreateWithoutPassword(String login, String firstName) {
        CourierModel courierModel = new CourierModel();
        courierModel.setLogin(login);
        courierModel.setFirstName(firstName);
        return sendPostRequest(courierUri, courierModel);
    }

    @Step("Check response status 201 and response body")
    public void successCourierCreated(Response response) {
        response.then().log().all()
                .assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(SC_CREATED);
    }

    @Step("Check response status 409 and response body")
    public void duplicateCourierCreated(Response response) {
        response.then().log().all()
                .assertThat().body("message", equalTo("Этот логин уже используется"))
                .and()
                .statusCode(SC_CONFLICT);
    }

    @Step("Check response status 400 and response body")
    public void withoutLoginCourierCreated(Response response) {
        response.then().log().all()
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    @Step("Check response status 400 and response body")
    public void withoutPasswordCourierCreated(Response response) {
        response.then().log().all()
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    @Step("Check response status 400 and response body")
    public void emptyLoginCourierCreated(Response response) {
        response.then().log().all()
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    @Step("Check response status 400 and response body")
    public void emptyPasswordCourierCreated(Response response) {
        response.then().log().all()
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    @Step("Check response status 400 and response body")
    public Response authCourier(String login, String password) {
        CourierModel courierModel = new CourierModel(login, password);
        return sendPostRequest(loginUri, courierModel);
    }

    @Step("Check successful authorization courier")
    public void authCourierSuccess(Response response) {
        response.then().log().all()
                .assertThat().body("id", notNullValue())
                .and()
                .statusCode(SC_OK);
    }

    @Step("Check authorization courier without key \"login\"")
    public Response authCourierWithoutLogin(String password) {
        CourierModel courierModel = new CourierModel();
        courierModel.setPassword(password);
        return sendPostRequest(loginUri, courierModel);
    }

    @Step("Check response status 400 and response body")
    public void withoutLoginCourierAuthorized(Response response) {
        response.then().log().all()
                .assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    @Step("Check authorization courier without key \"password\"")
    public Response authCourierWithoutPassword(String login) {
        CourierModel courierModel = new CourierModel();
        courierModel.setLogin(login);
        return sendPostRequest(loginUri, courierModel);
    }

    @Step("Check response status 400 and response body")
    public void withoutPasswordCourierAuthorized(Response response) {
        response.then().log().all()
                .assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    @Step("Check authorization courier with empty value of key \"login\"")
    public Response authCourierEmptyLogin(String login, String password) {
        login = "";
        CourierModel courierModel = new CourierModel(login, password);
        return sendPostRequest(loginUri, courierModel);
    }

    @Step("Check response status 400 and response body")
    public void emptyLoginCourierAuthorized(Response response) {
        response.then().log().all()
                .assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    @Step("Check authorization courier with empty value of key \"password\"")
    public Response authCourierEmptyPassword(String login, String password) {
        password = "";
        CourierModel courierModel = new CourierModel(login, password);
        return sendPostRequest(loginUri, courierModel);
    }

    @Step("Check response status 400 and response body")
    public void emptyPasswordCourierAuthorized(Response response) {
        response.then().log().all()
                .assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    @Step("Check authorization courier with incorrect value of key \"login\"")
    public Response authCourierLoginIncorrect(String login, String password) {
        login = RandomStringUtils.randomAlphanumeric(4,8);
        CourierModel courierModel = new CourierModel(login, password);
        return sendPostRequest(loginUri, courierModel);
    }

    @Step("Check response status 404 and response body")
    public void incorrectLoginCourierAuthorized(Response response) {
        response.then().log().all()
                .assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(SC_NOT_FOUND);
    }

    @Step("Check authorization courier with incorrect value of key \"password\"")
    public Response authCourierPasswordIncorrect(String login, String password) {
        password = RandomStringUtils.randomAlphanumeric(4,8);
        CourierModel courierModel = new CourierModel(login, password);
        return sendPostRequest(loginUri, courierModel);
    }

    @Step("Check response status 404 and response body")
    public void incorrectPasswordCourierAuthorized(Response response) {
        response.then().log().all()
                .assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(SC_NOT_FOUND);
    }

    @Step("Check get courier id")
    public int getCourierId(String login, String password) {
        CourierModel courierModel = new CourierModel(login, password);
        return sendPostRequest(loginUri, courierModel)
                .then().extract().path("id");
    }

    @Step("Check delete courier by id")
    public Response deleteCourierById(int id) {
        CourierModel courierModel = new CourierModel(id);
        return sendDeleteRequest(courierUri + "/" + id, courierModel);
    }
}
