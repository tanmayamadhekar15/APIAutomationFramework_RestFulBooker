package com.automationtestingrestassured.misc;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class DemoTest {
    @Test
            public void getPing(){
        RestAssured.
                given()
                .baseUri("https://restful-booker.herokuapp.com/")
                .basePath("/ping")
                .when().get().
                then().
                statusCode(201);
    }
}
