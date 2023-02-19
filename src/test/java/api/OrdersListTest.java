package api;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

public class OrdersListTest {
    private OrdersApi ordersApi;

    @Test
    @DisplayName("Get orders")
    @Description("Получение списка всех заказов")
    public void checkOrdersList() {
        Response response = ordersApi.getOrdersList();
        ordersApi.successOrderListGot(response);
    }

    @Before
    public void setUp() {
        ordersApi = new OrdersApi();
    }
}
