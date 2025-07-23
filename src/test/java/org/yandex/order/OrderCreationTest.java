package org.yandex.order;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.yandex.order.core.BaseOrderTest;
import org.yandex.order.model.Order;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreationTest extends BaseOrderTest {

    private final List<String> color;

    public OrderCreationTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "Создание заказа с цветами: {0}")
    public static Object[][] colors() {
        return new Object[][]{
                {Collections.singletonList("BLACK")},
                {Collections.singletonList("GREY")},
                {Arrays.asList("BLACK", "GREY")},
                {Collections.emptyList()}
        };
    }

    @Test
    @DisplayName("Создание заказа")
    public void createOrderTest() {
        Order order = orderSteps.generateBaseOrder();
        order.setColor(color);

        orderSteps.createOrder(order)
                .then()
                .statusCode(201)
                .body("track", notNullValue());
    }
}
