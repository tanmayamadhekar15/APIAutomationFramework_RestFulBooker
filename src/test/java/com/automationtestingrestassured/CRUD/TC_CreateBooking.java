package com.automationtestingrestassured.CRUD;

import com.automationtestingrestassured.actions.AssertActions;
import com.automationtestingrestassured.base.BaseTest;
import com.automationtestingrestassured.endPoints.APIConstants;
import com.automationtestingrestassured.modules.PayloadManager;
import com.automationtestingrestassured.payLoad.response.BookingResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TC_CreateBooking extends BaseTest {
    AssertActions actions=new AssertActions();
    @Test(groups={"qa"})
    @Owner("TM")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify booking is created with no payload")
    public void testCreateBookingNegative() throws JsonProcessingException {
        actions=new AssertActions();
        rs.basePath(APIConstants.CREATE_BOOKING);
        rs.body("");

        res = rs.when().post();

        vr = res.then().log().all();
        //vr.statusCode(500);
        actions.verifyStatusCode(vr.extract().response());
    }

    @Test(groups={"qa"})
    @Owner("TM")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify booking is created and ID is generated")
    public void testCreateBooking() throws JsonProcessingException {
        /*pm=new PayloadManager();
        actions=new AssertActions();
        rs= RestAssured.given()
                .baseUri(APIConstants.BASE_URL)
                .contentType(ContentType.JSON).log().all();*/
        rs.basePath(APIConstants.CREATE_BOOKING);
        rs.body(pm.createPayload());

        res= rs.when().post();

        vr=res.then().log().all();
        vr.statusCode(200);

        jsonPath= JsonPath.from(res.asString());  //Serialization
        String bookingid=jsonPath.getString("bookingid");
                //OR
        BookingResponse bookingResponse=pm.JSONToObject(res.asString());  //Deserialization
        assertThat(bookingResponse.getBookingid().toString()).isNotNull().isNotEmpty();
    }


}
