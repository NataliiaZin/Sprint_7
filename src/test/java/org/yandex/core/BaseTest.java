package org.yandex.core;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public abstract class BaseTest {

    private static final String BASE_URI = "https://qa-scooter.praktikum-services.ru/";

    @BeforeClass
    public static void setUpClass() {
        RestAssured.baseURI = BASE_URI;
    }
}
