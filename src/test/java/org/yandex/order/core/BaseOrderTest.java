package org.yandex.order.core;

import org.junit.After;
import org.junit.Before;
import org.yandex.core.BaseTest;
import org.yandex.order.model.Order;
import org.yandex.order.steps.OrderSteps;

public class BaseOrderTest extends BaseTest {

    protected OrderSteps orderSteps = new OrderSteps();
    protected Order order;

    @Before
    public void setUp() {
        order = orderSteps.generateBaseOrder();
    }

    @After
    public void tearDown() {
        orderSteps.declineOrder(order.getTrack());
    }
}
