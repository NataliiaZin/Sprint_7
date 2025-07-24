package org.yandex.courier;

import io.qameta.allure.junit4.DisplayName;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.yandex.courier.core.BaseCourierTest;

import static org.hamcrest.Matchers.*;


public class CourierCreationCourierTest extends BaseCourierTest {

    @Test
    @DisplayName("Курьер создается")
    public void createCourierSuccessfullyTest() {
        courierSteps.createCourier(courier)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("ok", is(true));
    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров")
    public void cannotCreateDuplicateCourierTest() {
        courierSteps.createCourier(courier)
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        courierSteps.createCourier(courier)
                .then()
                .statusCode(HttpStatus.SC_CONFLICT)
                .body("message", containsString("Этот логин уже используется"));
    }

    @Test
    @DisplayName("Нельзя создать курьера без логина")
    public void cannotCreateCourierWithoutLoginTest() {
        courier.setPassword(null);
        courierSteps.createCourier(courier)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Нельзя создать курьера без пароля")
    public void cannotCreateCourierWithoutPasswordTest() {
        courier.setLogin(null);
        courierSteps.createCourier(courier)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}