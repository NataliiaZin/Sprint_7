package org.yandex.courier;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.yandex.courier.core.BaseCourierTest;

import static org.hamcrest.Matchers.*;
import static org.yandex.util.StringUtils.generateRandomString;

public class CourierLoginTest extends BaseCourierTest {

    @Test
    @DisplayName("Успешная авторизация")
    public void courierCanLoginSuccessfullyTest() {
        courierSteps.createCourier(courier);
        courierSteps.loginCourier(courier)
                .then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Неуспешная авторизация с незаполненным логином")
    public void loginFailsWithoutLoginTest() {
        courierSteps.createCourier(courier);
        courier.setLogin(null);
        courierSteps.sendLoginRequest(courier)
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Неуспешная авторизация с незаполненным паролем")
    public void loginFailsWithoutPasswordTest() {
        courierSteps.createCourier(courier);
        courier.setPassword(null);
        courierSteps.sendLoginRequest(courier)
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Неуспешная авторизация с неправильным логином")
    public void loginFailsWithWrongLoginTest() {
        courierSteps.createCourier(courier);
        courier.setLogin(generateRandomString(12));
        courierSteps.loginCourier(courier)
                .then()
                .statusCode(404)
                .body("message", containsString("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Неуспешная авторизация с неправильным паролем")
    public void loginFailsWithWrongPasswordTest() {
        courierSteps.createCourier(courier);
        courier.setPassword(generateRandomString(12));
        courierSteps.loginCourier(courier)
                .then()
                .statusCode(404)
                .body("message", containsString("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Неуспешная авторизация несуществующего курьера")
    public void loginFailsIfCourierNotExistTest() {
        courierSteps.loginCourier(courierSteps.generateCourier())
                .then()
                .statusCode(404)
                .body("message", containsString("Учетная запись не найдена"));
    }
}

