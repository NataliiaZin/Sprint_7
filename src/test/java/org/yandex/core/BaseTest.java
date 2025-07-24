package org.yandex.core;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.yandex.core.constants.Endpoints;

public abstract class BaseTest {

    @BeforeClass
    public static void setUpClass() {
        RestAssured.baseURI = Endpoints.BASE_URI;
    }
}
