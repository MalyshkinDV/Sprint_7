package api;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RunWith(Parameterized.class)
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
    private final List<String> color;

    public OrderCreateTest(List<String> color)
    {
        this.color = color;
    }

    @Parameterized.Parameters(name = "Order. Color: {0}")
    public static Object[][] setValues() {
        return new Object[][] {
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("BLACK, GREY")},
                {List.of("")}
        };
    }

    @Test
    @DisplayName("Create order with key \"color\"")
    @Description("Создание заказа с разными значениями ключа \"color\"")
    public void checkCreateOrder() {
        Response response = ordersApi.createOrder(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
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
