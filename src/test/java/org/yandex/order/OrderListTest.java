package org.yandex.order;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.yandex.order.core.BaseOrderTest;

import java.util.List;

import static org.hamcrest.Matchers.*;

public class OrderListTest extends BaseOrderTest {

    @Test
    @DisplayName("Получить все заказы")
    public void getAllOrdersTest() {
        orderSteps.createOrder(orderSteps.generateBaseOrder());
        orderSteps.getOrders()
                .then()
                .statusCode(200)
                .body("orders", is(notNullValue()))
                .body("orders", instanceOf(List.class));
    }
}
