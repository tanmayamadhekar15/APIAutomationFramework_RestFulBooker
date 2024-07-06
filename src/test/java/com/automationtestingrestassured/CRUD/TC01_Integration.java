package com.automationtestingrestassured.CRUD;

import com.automationtestingrestassured.base.BaseTest;
import com.automationtestingrestassured.endPoints.APIConstants;
import com.automationtestingrestassured.payLoad.request.Booking;
import com.automationtestingrestassured.payLoad.response.BookingResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;


import static org.assertj.core.api.Assertions.assertThat;

public class TC01_Integration extends BaseTest {

    String token;
    String bookingid;
    String bookingidUsingPOJO;

    //private static final Logger log= (Logger) LogManager.getLogger(TC_CreateBookingUsingFilloFile.class);
    //Create a booking
    @Test(groups = "P0",priority = 2)
    public void testCreateBooking() throws JsonProcessingException {
        token=getAToken();
        System.out.println(token);
        assertThat(token).isNotNull().isNotEmpty();
                            rs.contentType(ContentType.JSON).log().all();
                            rs.basePath(APIConstants.CREATE_BOOKING);
                            rs.body(pm.createPayload());

            res= rs.when().post();

            vr=res.then().log().all();
            vr.statusCode(200);

        jsonPath= JsonPath.from(res.asString());  //Serialization
        // Direct extract from Jsonpath
        String bookingid=jsonPath.getString("bookingid");
            System.out.println("Booking ID given by JSONPath : " +bookingid);
        assertThat(bookingid).isNotEmpty().isNotNull();

            //OR

       //Using bookingResponse class
        BookingResponse bookingResponse=pm.JSONToObject(res.asString());//Deserialization
        String bookingidUsingPOJO=bookingResponse.getBookingid().toString();
            System.out.println("Booking ID given by POJO : " + bookingidUsingPOJO);
        assertThat(bookingidUsingPOJO).isNotEmpty().isNotNull();

           //assertThat(bookingResponse.getBookingid().toString()).isNotNull().isNotEmpty();
    }


    //Get a token
    @Test(groups = "P0",priority = 1)
    public String getAToken() throws JsonProcessingException {
        String authPayload= pm.setToken();
                rs.basePath("/auth");
                rs.contentType(ContentType.JSON);
                rs.body(authPayload);

                res=rs.when().post();

                vr=res.then().log().all().statusCode(200);
        jsonPath= new JsonPath(res.asString()); //Serialization
        return jsonPath.getString("token");
    }


    //Update booking
   @Test(groups = "P0",dependsOnMethods = {"testCreateBooking"},priority = 3)
    public void updateBooking() throws JsonProcessingException {
       jsonPath= JsonPath.from(res.asString());  //Serialization
       // Direct extract from Jsonpath
      /* String bookingid;
       bookingid = jsonPath.getString("bookingid");
       System.out.println("Booking ID given by JSONPath : " +bookingid);
*/              //token=getAToken();
                rs.basePath(APIConstants.UPDATE_BOOKING+"/"+bookingid);
               rs.contentType(ContentType.JSON).log().all();
       rs.cookie("token",token);
       rs.body(pm.updatePayload());

       res= rs.when().put();

       vr=res.then().log().all();
      /* vr.body("firstname", Matchers.is("Annie"));
       Using bookingResponse class
       Booking booking=pm.JSONToObjectPut(res.asString());//Deserialization
       booking.getFirstname().toString();
       System.out.println("Firstname : " +booking.getFirstname().toString());*/
   }


   //Delete a booking
   @Test(groups = {"P0"},dependsOnMethods = {"testCreateBooking"},priority = 4)
    public void deleteBooking() throws JsonProcessingException {
       jsonPath= JsonPath.from(res.asString());  //Serialization
       // Direct extract from Jsonpath
       String bookingid=jsonPath.getString("bookingid");
      // System.out.println("Booking ID given by JSONPath : " +bookingid);


       //OR

       //Using bookingResponse class
       BookingResponse bookingResponse;//Deserialization
       bookingResponse = pm.JSONToObject(res.asString());
       String bookingidUsingPOJO=bookingResponse.getBookingid().toString();
       System.out.println("Booking ID given by POJO : " + bookingidUsingPOJO);
       token=getAToken();
       System.out.println(token+ " " + bookingid + " " +bookingidUsingPOJO);
       rs.basePath(APIConstants.DELETE_BOOKING+"/"+bookingid);
       rs.contentType(ContentType.JSON).log().all();
       rs.cookie("token",token);

       res= rs.when().delete();

       vr=res.then().log().all().statusCode(201);

   }
}
