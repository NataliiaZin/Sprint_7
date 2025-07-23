package org.yandex.order.steps;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.yandex.order.model.Order;

public class OrderSteps {

    @Step("Создать заказ с незаполненным color")
    public Order generateBaseOrder() {
        return new Order()
                .setFirstName("Naruto")
                .setLastName("Uchiha")
                .setAddress("Konoha, 142 apt.")
                .setMetroStation("station")
                .setPhone("+7 800 355 35 35")
                .setRentTime(5)
                .setDeliveryDate("2020-06-06")
                .setComment("Saske, come back to Konoha");
    }

    @Step("Создать заказ")
    public Response createOrder(Order order) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(order)
                .post("/api/v1/orders");
    }

    @Step("Получить список всех заказов")
    public Response getOrders() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .get("/api/v1/orders");
    }
}
