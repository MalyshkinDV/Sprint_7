package api;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(JUnitParamsRunner.class)
public class OrderCreateTest {
    private OrdersApi ordersApi;
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;

    @Test
    /*@DisplayName("Create order with different values of key \"color\"") // don't know how to use it with parameterized test
    @Description("Создание заказа с разными значениями ключа \"color\"")*/
    @Parameters({"BLACK", "GREY", "BLACK, GREY", ""})
    public void checkCreateOrder(String[] color) {
        Response response = ordersApi.createOrder(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        ordersApi.successOrderCreated(response);
    }

    @Test
    @DisplayName("Create order without key \"color\"")
    @Description("Создание заказа без ключа \"color\"")
    public void checkCreateOrder() {
        Response response = ordersApi.createOrderWithoutColor(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment);
        ordersApi.successOrderCreated(response);
    }

    @Before
    public void setUp() {
        ordersApi = new OrdersApi();
        firstName = RandomStringUtils.randomAlphabetic(4, 8);
        lastName = RandomStringUtils.randomAlphabetic(4, 8);
        address = RandomStringUtils.randomAlphabetic(4, 8);
        metroStation = RandomStringUtils.randomNumeric(1, 2);
        phone = RandomStringUtils.randomNumeric(11);
        rentTime = (int) (Math.random()*30);
        deliveryDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        comment = RandomStringUtils.randomAlphabetic(4, 8);
    }
}
