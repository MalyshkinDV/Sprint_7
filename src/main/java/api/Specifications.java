package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Model;

import static io.restassured.RestAssured.given;

public class Specifications {
    private static final String baseUrl = "http://qa-scooter.praktikum-services.ru";

    protected  RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .build();
    }

    protected final Response sendPostRequest(String uri, Model body) {
        return given()
                .spec(requestSpec())
                .body(body)
                .post(uri);
    }

    protected final Response sendGetRequest(String uri) {
        return given()
                .spec(requestSpec())
                .get(uri);
    }

    protected final Response sendDeleteRequest(String uri, Model body) {
        return given()
                .spec(requestSpec())
                .body(body)
                .delete(uri);
    }
}
