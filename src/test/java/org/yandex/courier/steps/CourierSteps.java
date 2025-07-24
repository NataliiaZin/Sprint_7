package org.yandex.courier.steps;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.yandex.core.constants.Endpoints;
import org.yandex.courier.model.Courier;

import static org.yandex.util.StringUtils.generateRandomString;

public class CourierSteps {

    @Step("Сгенерировать курьера со случайным набором логин пароль")
    public Courier generateCourier() {
        return new Courier()
                .setLogin(generateRandomString(12))
                .setPassword(generateRandomString(12));
    }

    @Step("Создать курьера")
    public Response createCourier(Courier courier) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(courier)
                .post(Endpoints.COURIER_ENDPOINT);
    }

    @Step("Войти под курьером")
    public Response loginCourier(Courier courier) {
        Response res = sendLoginRequest(courier);
        if (res.statusCode() == HttpStatus.SC_CREATED) {
            courier.setId(res
                    .then()
                    .extract()
                    .path("id"));
        }
        return res;
    }

    @Step("Удалить курьера с ID {id}")
    public void deleteCourier(int id) {
        RestAssured.given()
                .delete(Endpoints.COURIER_ENDPOINT + id);
    }

    @Step("Отправка запроса логина")
    public Response sendLoginRequest(Courier courier) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(courier)
                .post(Endpoints.COURIER_LOGIN_ENDPOINT);
    }

}
