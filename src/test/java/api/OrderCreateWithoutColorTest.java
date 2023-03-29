package api;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderCreateWithoutColor {
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
    @DisplayName("Create order without key \"color\"")
    @Description("Создание заказа без ключа \"color\"")
    public void checkCreateOrderWithoutColor() {
        Response response = ordersApi.createOrderWithoutColor(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment);
        ordersApi.successOrderCreated(response);
    }

    @Before
    public void setUp() {
        ordersApi = new OrdersApi();
        firstName = RandomStringUtils.randomAlphabetic(4, 8);
        lastName = RandomStringUtils.randomAlphabetic(5, 10);
        address = RandomStringUtils.randomAlphabetic(4, 8);
        metroStation = RandomStringUtils.randomNumeric(1, 2);
        phone = RandomStringUtils.randomNumeric(11);
        rentTime = (int) (Math.random()*30);
        deliveryDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        comment = RandomStringUtils.randomAlphabetic(4, 8);
    }
}
