package com.automationtestingrestassured.base;

import com.automationtestingrestassured.actions.AssertActions;
import com.automationtestingrestassured.endPoints.APIConstants;
import com.automationtestingrestassured.modules.PayloadManager;
import com.automationtestingrestassured.modules.PayloadManagerUsingGSON;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;


public class BaseTestUsingYAMLFile {

    public static RequestSpecification rs;
    public static Response res;
    public static ValidatableResponse vr;
    public static AssertActions actions;
    public static PayloadManager pm;
    public static JsonPath jsonPath;
    public static PayloadManagerUsingGSON payloadGSON;

    @BeforeClass(alwaysRun = true)
    public void setConfig() {
        pm = new PayloadManager();
        payloadGSON = new PayloadManagerUsingGSON();
        actions = new AssertActions();
         rs=RestAssured.given().baseUri(APIConstants.BASEURL_FromYAML).log().all();

    }

    public String getToken() {
                rs.baseUri(APIConstants.BASEURL_FromYAML);
                rs.basePath(APIConstants.AUTH_URL);

        String payload = payloadGSON.setAuthPayload();
        res = rs
                .contentType(ContentType.JSON)
                .body(payload)
                .when().post();

        String token = payloadGSON.getTokenJSON(res.asString());
        return token;

    }
}