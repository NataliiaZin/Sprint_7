package org.yandex.courier.core;

import org.junit.After;
import org.junit.Before;
import org.yandex.core.BaseTest;
import org.yandex.courier.model.Courier;
import org.yandex.courier.steps.CourierSteps;

public abstract class BaseCourierTest extends BaseTest {

    protected Courier courier;
    protected final CourierSteps courierSteps = new CourierSteps();

    @Before
    public void setUp() {
        courier = courierSteps.generateCourier();
    }

    @After
    public void tearDown() {
        if (courier.getLogin() != null && courier.getPassword() != null) {
            courierSteps.loginCourier(courier);
            if (courier.getId() != null) {
                courierSteps.deleteCourier(courier.getId());
            }
        }
    }
}