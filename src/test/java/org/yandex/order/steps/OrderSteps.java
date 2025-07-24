package org.yandex.order.steps;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.yandex.core.constants.Endpoints;
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
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(order)
                .post(Endpoints.ORDERS_ENDPOINT);
        if (response.statusCode() == HttpStatus.SC_CREATED) {
            order.setTrack(response.then()
                    .extract()
                    .path("track")
            );
        }
        return response;
    }

    @Step("Отменить заказ")
    public void declineOrder(int track) {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(track)
                .put(Endpoints.ORDERS_DECLINE_ENDPOINT);
    }

    @Step("Получить список всех заказов")
    public Response getOrders() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .get(Endpoints.ORDERS_ENDPOINT);
    }
}
