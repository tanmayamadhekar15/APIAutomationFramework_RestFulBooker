package com.automationtestingrestassured.CRUD;

import com.automationtestingrestassured.base.BaseTest;
import com.automationtestingrestassured.base.BaseTestUsingFillo;
import com.automationtestingrestassured.endPoints.APIConstants;
import com.automationtestingrestassured.payLoad.response.BookingResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TC_CreateBookingUsingFilloFile extends BaseTestUsingFillo {

    @Test(groups={"qa"})
    @Owner("TM")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify booking is created with no payload")
    public void testCreateBookingNegative() throws JsonProcessingException {

        rs.basePath(APIConstants.CREATE);
        rs.body("");
        res = rs.when().post();
        vr = res.then().log().all();
        //vr.statusCode(500);
        this.actions.verifyStatusCode(vr.extract().response());
    }

    @Test(groups={"qa"})
    @Owner("TM")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify booking is created and ID is generated")
    public void testCreateBooking() throws JsonProcessingException {
        rs.basePath(APIConstants.CREATE);
        rs.body(pm.createPayload());
        rs.contentType(ContentType.JSON);
        res= rs.when().post();

        vr=res.then().log().all().statusCode(200);

        jsonPath= JsonPath.from(res.asString());  //Serialization
        String bookingid=jsonPath.getString("bookingid");
                //OR
        BookingResponse bookingResponse=pm.JSONToObject(res.asString());  //Deserialization
        assertThat(bookingResponse.getBookingid().toString()).isNotNull().isNotEmpty();
    }


}
