package com.automationtestingrestassured.CRUD;

import com.automationtestingrestassured.base.BaseTest;
import com.automationtestingrestassured.base.BaseTestUsingPropertyFile;
import com.automationtestingrestassured.endPoints.APIConstants;
import com.automationtestingrestassured.payLoad.request.Booking;
import com.automationtestingrestassured.payLoad.response.BookingResponse;
import com.automationtestingrestassured.utils.TestDataPropertyReader;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TC02_IntegrationUsingPropertyFile extends BaseTestUsingPropertyFile {

    @Test(priority = 1)
    public void test_createBooking(ITestContext itest) {
        itest.setAttribute("token", getToken());
        //rs.baseUri(APIConstants.BASE_URL_usingPropertyFile);
        rs.basePath(APIConstants.CREATE_BOOKING_usingPropertyFile);
        rs.body(payloadGSON.createBookingPayload());

        res=rs.when().log().all().post();

        vr=res.then().log().all();
                vr.statusCode(200);
        BookingResponse bookingResponse = payloadGSON.bookingResponseJava(res.asString());
        // AssertJ
        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotBlank();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("Annie");

        itest.setAttribute("bookingid",bookingResponse.getBookingid());


    }

    @Test(priority = 2)
    public void test_getBooking(ITestContext itest) throws IOException {

        Integer bookingid = (Integer) itest.getAttribute("bookingid");
        rs.basePath(APIConstants.CREATE_BOOKING_usingPropertyFile+"/"+bookingid);
        res = RestAssured
                .given(rs)
                .when().get();
        vr = res.then().log().all();
        vr.statusCode(200);

        Booking booking = payloadGSON.getResponseFromJSON(res.asString());

        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo(TestDataPropertyReader.readAKeyFromPropertyFile("booking.firstname"));

        itest.setAttribute("bookingid",bookingid);

    }

    @Test(priority = 3)
    public void test_updateBooking(ITestContext itest){
        String token = (String) itest.getAttribute("token");
        Integer bookingid = (Integer) itest.getAttribute("bookingid");

        res = RestAssured
                .given(rs).cookie("token", token)
                .when().body(payloadGSON.putPayload()).put();
        vr = res.then().log().all();

        vr.statusCode(200);

        Booking booking = payloadGSON.getResponseFromJSON(res.asString());

        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getLastname()).isEqualTo("Benz");

    }

    @Test(priority = 4)
    public void test_deleteBooking(ITestContext itest){
        Integer bookingid=(Integer)itest.getAttribute("bookingid");
        String token=(String) itest.getAttribute("token");

        rs.basePath(APIConstants.DELETE_BOOKING+"/"+bookingid);
        rs.cookie("token",token);

        res=rs.when().delete();

        vr=res.then().log().all().statusCode(201);
        vr.assertThat().statusLine("HTTP/1.1 201 Created");

    }
}
