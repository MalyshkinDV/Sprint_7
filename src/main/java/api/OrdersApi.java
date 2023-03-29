package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.request.OrderModel;
import models.response.AvailableStationsModel;
import models.response.OrdersListModel;
import models.response.OrdersModel;
import models.response.PageInfoModel;
import org.hamcrest.MatcherAssert;

import java.util.List;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.notNullValue;

public class OrdersApi extends Specifications{
    private final String ordersUri = "/api/v1/orders";

    @Step("Check create new order")
    public Response createOrder(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> color
    ) {
        OrderModel orderModel = new OrderModel(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        return sendPostRequest(ordersUri, orderModel);
    }

    @Step("Check create new order without key \"color\"")
    public Response createOrderWithoutColor(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment) {
        OrderModel orderModel = new OrderModel(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment);
        return sendPostRequest(ordersUri, orderModel);
    }

    @Step("Check response status 201 and response body")
    public void successOrderCreated(Response response) {
        response.then().log().all()
                .assertThat().body("track", notNullValue())
                .and()
                .statusCode(SC_CREATED);
    }

    @Step("Check get orders")
    public Response getOrdersList() {
        return sendGetRequest(ordersUri);
    }

    @Step("Check response status 200 and response body is not null")
    public void successOrderListGot(Response response) {
        response.then().log().all()
                .assertThat().statusCode(SC_OK);
        OrdersListModel ordersListModel = response.body().as(OrdersListModel.class);
        OrdersModel[] orders = ordersListModel.getOrders();
        PageInfoModel pageInfo = ordersListModel.getPageInfo();
        AvailableStationsModel[] availableStations = ordersListModel.getAvailableStations();
        MatcherAssert.assertThat("Response is null",ordersListModel, notNullValue());
        MatcherAssert.assertThat("Object \"orders\" is null",orders, notNullValue());
        MatcherAssert.assertThat("Object \"pageInfo\" is null",pageInfo, notNullValue());
        MatcherAssert.assertThat("Object \"availableStations\" is null",availableStations, notNullValue());
    }
}
