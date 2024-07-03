package com.automationtestingrestassured.schema;

import io.restassured.RestAssured;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class SchemaValidation {

    @Test
    void JSONValidation(){
        RestAssured.given()
                .when().get("https://restful-booker.herokuapp.com/booking")
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("/src/test/java/com/automationtestingrestassured/schema/postResponseJsonSchema.json"));
    }
    @Test
    void XMLValidation() {
        RestAssured.given()
                .when().get("https://restful-booker.herokuapp.com/booking")
                .then()
                .assertThat().body(RestAssuredMatchers.matchesXsd("/src/test/java/com/automationtestingrestassured/schema/XMLSchema.xsd"));
    }

}
